package vn.com.rfim_api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.com.rfim_api.algorithm.ShelfGraphConverter;
import vn.com.rfim_api.algorithm.ShortestPathAlgorithm;
import vn.com.rfim_api.persistences.entities.Invoice;
import vn.com.rfim_api.persistences.entities.Package;
import vn.com.rfim_api.persistences.entities.Shelf;
import vn.com.rfim_api.persistences.repositories.InvoiceRepository;
import vn.com.rfim_api.persistences.repositories.PackageRepository;
import vn.com.rfim_api.persistences.repositories.ShelfRepository;
import vn.com.rfim_api.services.jsonobjects.InvoiceInfoItem;
import vn.com.rfim_api.services.jsonobjects.SortIssueInfo;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceContext;
    @Autowired
    private PackageRepository packageContext;
    @Autowired
    private ShelfRepository shelfContext;

    //Get invoice have status false
    public ResponseEntity getInvoiceInfo() {
        List<InvoiceInfoItem> invoices = invoiceContext.getInvoiceInfo(2, false);
        if (invoices.size() > 0) {
            return new ResponseEntity(invoices, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    //Use dijktra algorithm to sort list which item should be pick first
    public ResponseEntity sortIssueInvoiceItem(List<InvoiceInfoItem> invoices) {
        List<SortIssueInfo> result = new ArrayList<>();
        List<InvoiceInfoItem> results = new ArrayList<>();
        List<Shelf> shelves = shelfContext.getAll();
        Shelf currentShelf = null;
        String currentProductId = null;
        InvoiceInfoItem currentInvoiceInfo = null;
        int currentPosition = 0;
        int shortestDistance = Integer.MAX_VALUE;
        List<String> productIds = new ArrayList<>();
        List<String> tmpListProductId = new ArrayList<>();
        List<InvoiceInfoItem> tmpListInvoiceInfo = new ArrayList<>();
        for (InvoiceInfoItem info: invoices) {
            productIds.add(info.getProductId());
            tmpListProductId.add(info.getProductId());
            tmpListInvoiceInfo.add(info);
        }
        ShelfGraphConverter convert = new ShelfGraphConverter(shelves);
        int[][] graph = convert.drawGraph();

        for (int i = 0; i < shelves.size(); i++) {
            for (int j = 0; j < shelves.size(); j++) {
                System.out.print(graph[i][j]);
            }
            System.out.println();
        }
        ShortestPathAlgorithm algorithm = new ShortestPathAlgorithm(shelves.size());
        int size = invoices.size();
//        for (int i = 0; i < productIds.size(); i++) {
        for (int i = 0; i < size; i++) {
            int[] dist = algorithm.dijkstra(graph, currentPosition);
            for (InvoiceInfoItem info : invoices) {
                Package pac = packageContext.getEarliesPackageByProductId(info.getProductId());
                Shelf shelf = shelfContext.getByPackageRfid(pac.getCell().getCellId());
                int position = convert.convertCoordinateToPosition(shelves, shelf);
                int distance = dist[position];
                if (distance < shortestDistance) {
                    shortestDistance = distance;
                    currentPosition = position;
                    currentProductId = info.getProductId();
                    currentShelf = shelf;

                    currentInvoiceInfo = info;
                    info.setShelfId(shelf.getShelfId());
                }
            }
            SortIssueInfo info = new SortIssueInfo(currentProductId, currentShelf.getShelfId());
            result.add(info);
            results.add(currentInvoiceInfo);
            System.out.println("Position will be remove: " + currentProductId + " " + currentShelf.getShelfId());
            tmpListProductId.remove(currentProductId);
            shortestDistance = Integer.MAX_VALUE;
        }
        return new ResponseEntity(result, HttpStatus.OK);
    }

}
