package vn.com.rfim_api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.com.rfim_api.algorithm.ShelfGraphConverter;
import vn.com.rfim_api.algorithm.ShortestPathAlgorithm;
import vn.com.rfim_api.persistences.entities.Package;
import vn.com.rfim_api.persistences.entities.Shelf;
import vn.com.rfim_api.persistences.repositories.InvoiceRepository;
import vn.com.rfim_api.persistences.repositories.PackageRepository;
import vn.com.rfim_api.persistences.repositories.ShelfRepository;
import vn.com.rfim_api.services.jsonobjects.InvoiceInfoItem;

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

    //Get receipt invoice have status false
    public ResponseEntity getReceiptInfo() {
        List<InvoiceInfoItem> invoices = invoiceContext.getInvoiceInfo(1);
        List<InvoiceInfoItem> result = new ArrayList<>();
        int size = invoices.size();
        System.out.println("SIZE: " + size);
//        for (int i = 0; i < size; i++) {
////            System.out.println(invoices.get(i).getInvoiceId());
////            if (invoices.get(i).getStatus() == 3) {
////                invoices.remove(invoices.get(i));
////            }
////        }
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
        int size = invoices.size();
        for (int i = 0; i < size; i++) {
            if (invoices.get(i).getStatus() == 3) {
                invoices.remove(invoices.get(i));
            }
        }
        if (invoices.size() > 0) {
            return new ResponseEntity(invoices, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    //Use dijktra algorithm to sort list which item should be pick first
    public ResponseEntity sortIssueInvoiceItem(List<InvoiceInfoItem> invoices) {
        List<InvoiceInfoItem> results = new ArrayList<>();
        List<InvoiceInfoItem> notEnoughInvoie = new ArrayList<>();
        List<Shelf> shelves = shelfContext.getAll();
        InvoiceInfoItem currentInvoiceInfo = null;
        int currentPosition = 0;
        int shortestDistance = Integer.MAX_VALUE;
        boolean isUpdate = false;
//        List<InvoiceInfoItem> tmpListInvoiceInfo = new ArrayList<>();
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
        for (int i = 0; i < size; i++) {
            int[] dist = algorithm.dijkstra(graph, currentPosition);
            for (int j = 0; j < invoices.size(); j++) {
                InvoiceInfoItem info = invoices.get(j);
                Package pac = packageContext.getEarliesPackageByProductId(info.getProductId());
                if (pac != null) {
                    Shelf shelf = shelfContext.getByPackageRfid(pac.getCell().getCellId());
                    int position = convert.convertCoordinateToPosition(shelves, shelf);
                    int distance = dist[position];
                    if (distance < shortestDistance) {
                        shortestDistance = distance;
                        currentPosition = position;
                        currentInvoiceInfo = info;
                        info.setShelfId(shelf.getShelfId());
                    }
                    isUpdate = true;

                } else {
                    invoices.remove(info);
//                    info.setShelfId("N/A");
                    notEnoughInvoie.add(info);
                    isUpdate = false;
                }
            }

            if (isUpdate) {
                System.out.println("Position will be remove: " + currentInvoiceInfo.getProductId() + " " + currentInvoiceInfo.getShelfId());
                invoices.remove(currentInvoiceInfo);
                results.add(currentInvoiceInfo);
                shortestDistance = Integer.MAX_VALUE;
            }
        }
        for (InvoiceInfoItem i : notEnoughInvoie) {
            results.add(i);
        }
        return new ResponseEntity(results, HttpStatus.OK);
    }

}
