package vn.com.rfim_api.services;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.com.rfim_api.algorithm.ShelfGraphConverter;
import vn.com.rfim_api.algorithm.ShortestPathAlgorithm;
import vn.com.rfim_api.persistences.entities.ConstantTable;
import vn.com.rfim_api.persistences.entities.Shelf;
import vn.com.rfim_api.persistences.repositories.*;
import vn.com.rfim_api.services.jsonobjects.AlgorithmResult;
import vn.com.rfim_api.services.jsonobjects.CellInfo;
import vn.com.rfim_api.services.jsonobjects.InvoiceInfoItem;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceContext;
    @Autowired
    private ConstantTableRespository constantContext;
    @Autowired
    private ShelfRepository shelfContext;

    private int source = 0;

    //Get receipt invoice have status false
    public ResponseEntity getReceiptInfo() {
        List<InvoiceInfoItem> invoices = invoiceContext.getInvoiceInfo(1);
        List<InvoiceInfoItem> result = new ArrayList<>();
        int size = invoices.size();
        System.out.println("SIZE: " + size);
        for (InvoiceInfoItem i : invoices) {
            if (i.getStatus() != 3) {
                result.add(i);
            }
        }
        if (invoices.size() > 0) {
            return new ResponseEntity(result, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    //Get issue invoice have status false
    public ResponseEntity getIssueInfo() {
        List<InvoiceInfoItem> invoices = invoiceContext.getInvoiceInfo(2);
        List<InvoiceInfoItem> result = new ArrayList<>();
        int size = invoices.size();
        for (InvoiceInfoItem i : invoices) {
            if (i.getStatus() != 3) {
                result.add(i);
            }
        }
        if (invoices.size() > 0) {
            return new ResponseEntity(result, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    private int getDateFromNowToStockinDate(Date productDate) {
        DateTime nowDate = DateTime.now();
        DateTime pDate = new DateTime(productDate.getTime());
        Days days = Days.daysBetween(pDate, nowDate);
        return days.getDays();
    }

    private float calculateWeightedNumber(long date, long distance) {
        ConstantTable constant = constantContext.getConstantTable();

        float result = date * constant.getWeightedDate() + distance * constant.getWeightedDistance() * (-1);
        return result;
    }

    //Use dijktra algorithm to sort list which item should be pick first
    public ResponseEntity sortIssueInvoiceItem(InvoiceInfoItem invoice) {
        List<Shelf> shelves = shelfContext.getAll();
        ShelfGraphConverter convert = new ShelfGraphConverter(shelves);
        int[][] graph = convert.drawGraph(constantContext.getConstantTable().getColumnDistance(), constantContext.getConstantTable().getRowDistance());
//        for (int i = 0; i < graph.length; i++) {
//            for (int j = 0; j < graph.length; j++) {
//                System.out.print(graph[i][j]);
//            }
//            System.out.println();
//        }
        ShortestPathAlgorithm algorithm = new ShortestPathAlgorithm(shelves.size());
        Shelf sourceShelf = shelfContext.getByShelfId(constantContext.getConstantTable().getSourceShelfId());
        if (sourceShelf != null) {
            source = convert.convertCoordinateToPosition(shelves, sourceShelf);
        }
        int[] dist = algorithm.dijkstra(graph, source);
        List<Shelf> targetShelves = shelfContext.getShelvesContainProductId(invoice.getProductId());
        List<AlgorithmResult> rs = new ArrayList<>();
        for (Shelf s : targetShelves) {
            int position = convert.convertCoordinateToPosition(shelves, s);
            int distance = dist[position];
            List<CellInfo> cellInfos = shelfContext.getCellInfoOfShelf(s.getShelfId(), invoice.getProductId());
            int diffDate = getDateFromNowToStockinDate(shelfContext.getMinDateOfAProductId(s.getShelfId(), invoice.getProductId()));
            float weight = calculateWeightedNumber(diffDate, distance);
            AlgorithmResult ar = new AlgorithmResult(s.getShelfId(), weight, cellInfos);
            rs.add(ar);
        }
        Collections.sort(rs, new AlgorithmResult());
        return new ResponseEntity(rs, HttpStatus.OK);
    }

}
