package com.revature.ers.controller;

import com.revature.ers.model.Reimbursement;
import com.revature.ers.service.ReimbursementService;
import io.javalin.Javalin;
import io.javalin.http.Handler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ReimbursementController implements Controller {

    private Logger logger = LoggerFactory.getLogger(ReimbursementController.class);

    private ReimbursementService reimbursementService;

    public ReimbursementController() {
        this.reimbursementService = new ReimbursementService();
    }

    private Handler getAllReimbursements = (ctx) -> {

        List<Reimbursement> reimbursementList = reimbursementService.getAllReimbursements();

        logger.info("All reimbursements successfully retrieved");
        ctx.json(reimbursementList);
    };

    private Handler getAvgAmountGroupByEmployeeId = (ctx) -> {
        List<Reimbursement> userReimbursements = reimbursementService.getAvgAmountGroupByEmployeeId();

        if (!userReimbursements.isEmpty()) {
            logger.info("Reimbursements by user returned successfully.");
            ctx.json(userReimbursements);
        } else {
            ctx.result("User does not exist.");
        }

    };

    private Handler getSumAmountOrderByAmountAsc = (ctx) -> {
        List<Reimbursement> userReimbursements = reimbursementService.getSumAmountOrderByAmountAsc();

        if (!userReimbursements.isEmpty()) {
            logger.info("Reimbursements by user returned successfully.");
            ctx.json(userReimbursements);
        } else {
            ctx.result("User does not exist.");
        }

    };

    private Handler getSumAmountOrderByAmountDesc = (ctx) -> {
        List<Reimbursement> userReimbursements = reimbursementService.getSumAmountOrderByAmountDesc();

        if (!userReimbursements.isEmpty()) {
            logger.info("Reimbursements by user returned successfully.");
            ctx.json(userReimbursements);
        } else {
            ctx.result("User does not exist.");
        }

    };


    private Handler getReimbursementsByEmployeeID = (ctx) -> {
        String id = ctx.pathParam("id");

        List<Reimbursement> userReimbursements = reimbursementService.getReimbursementByEmployeeID(Integer.parseInt(id));

        if (!userReimbursements.isEmpty()) {
            logger.info("Reimbursements by user returned successfully.");
            ctx.json(userReimbursements);
        } else {
            logger.info("User with id: " + Integer.parseInt(id) + " does not exist.");
            ctx.result("User does not exist.");
        }

    };

    private Handler addReimbursement = ctx -> {
        Reimbursement reimbursement = ctx.bodyAsClass(Reimbursement.class);

        Reimbursement insertedReimbursement = reimbursementService.addReimbursement(reimbursement);

        if (insertedReimbursement.getReimbursement_id() != 0) {
            logger.info("New reimbursement successfully added.");
            ctx.json(insertedReimbursement);
        } else {
            logger.info("Something went wrong...try again");
            ctx.result("Something went wrong...try again");
        }

    };


    private Handler approveReimbursementById = ctx -> {

        String id = ctx.pathParam("id");

        Reimbursement reimbursement = ctx.bodyAsClass(Reimbursement.class);
        System.out.println(id);
        System.out.println(ctx.path());
        Reimbursement approvedReimbursement = reimbursementService.approveReimbursementById(Integer.parseInt(id),
                reimbursement);

        if (approvedReimbursement.getReimbursement_id() != 0) {
            logger.info("Approval request updated...");
            ctx.json(approvedReimbursement);
        } else {
            logger.info("Something went wrong...try again");
            ctx.result("Something went wrong...try again");
        }

    };


    @Override
    public void mapEndpoints(Javalin app) {

        app.get("/reimbursements", getAllReimbursements);
        app.post("/reimbursements", addReimbursement);
        app.get("/reimbursements/:id", getReimbursementsByEmployeeID);
        app.put("/reimbursements/:id", approveReimbursementById);
        app.get("/mean", getAvgAmountGroupByEmployeeId);
        app.get("/least", getSumAmountOrderByAmountAsc);
        app.get("/most", getSumAmountOrderByAmountDesc);
    }

}
