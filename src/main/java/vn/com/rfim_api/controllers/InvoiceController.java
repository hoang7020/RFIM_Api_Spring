package vn.com.rfim_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vn.com.rfim_api.services.InvoiceService;
import vn.com.rfim_api.services.jsonobjects.InvoiceInfoItem;
import vn.com.rfim_api.services.jsonobjects.RequestAlgortihm;

import java.util.ArrayList;
import java.util.List;

@RestController
@Transactional
public class InvoiceController {

    @Autowired
    private InvoiceService service;

    //Get all active issue (pending and processing)
    @GetMapping(value = "/issues")
    public ResponseEntity getAllActiveIssues() {
        return service.getIssueInfo();
    }

    //Get all active receipt (pending)
    @GetMapping(value = "/receipts")
    public ResponseEntity getAllActiveReceipt() {
        return service.getReceiptInfo();
    }

    //Suggest box using dijkstra and weighted average number
    @PostMapping(value = "/dijkstra")
    public ResponseEntity suggestBox(@RequestBody InvoiceInfoItem request) {
//        List<InvoiceInfoItem> invoices = request.getInvoiceInfoItems();
//        InvoiceInfoItem invoice = invoices.get(0);
        return service.sortIssueInvoiceItem(request);
    }

}
