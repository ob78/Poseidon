package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.services.ITradeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

import static java.sql.Timestamp.valueOf;
import static java.time.LocalDateTime.now;

@Controller
public class TradeController {

    private static final Logger logger = LoggerFactory.getLogger(TradeController.class);

    @Autowired
    ITradeService tradeService;

    @RequestMapping("/trade/list")
    public String home(Model model) {
        logger.info("Request : GET /trade/list");

        List<Trade> trades = tradeService.findAllTrades();
        model.addAttribute("trades", trades);

        logger.info("Success : trades found, returning 'trade/list' view");

        return "trade/list";
    }

    @GetMapping("/trade/add")
    public String addUser(Trade trade) {

        logger.info("Request : GET /trade/add");
        logger.info("Success : returning 'trade/add' view");

        return "trade/add";
    }

    @PostMapping("/trade/validate")
    public String validate(@Valid Trade trade, BindingResult result, Model model) {

        logger.info("Request : POST /trade/validate");

        if (!result.hasErrors()) {
            trade.setCreationDate(valueOf(now()));
            trade.setTradeDate(valueOf(now()));
            tradeService.createTrade(trade);

            logger.info("Success : new trade created, redirect to '/trade/list' view");

            return "redirect:/trade/list";
        }

        logger.error("Error in fields validation : new trade not created, returning '/trade/add' view");

        return "trade/add";
    }

    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

        logger.info("Request : GET /trade/update/{}", id);

        Trade trade = tradeService.findTradeById(id);
        model.addAttribute("trade", trade);

        logger.info("Success : trade with id {} to update found, returning '/trade/update' view", id);

        return "trade/update";
    }

    @PostMapping("/trade/update/{id}")
    public String updateTrade(@Valid Trade trade, BindingResult result, Model model) {

        logger.info("Request : POST /trade/update/{}", trade.getTradeId());

        if (result.hasErrors()) {

            logger.error("Error in fields : trade with id {} not updated, returning '/trade/update' view", trade.getTradeId());

            return "trade/update";
        }

        trade.setCreationDate(tradeService.findTradeById(trade.getTradeId()).getCreationDate());
        trade.setRevisionDate(valueOf(now()));
        trade.setTradeDate(valueOf(now()));
        tradeService.updateTrade(trade);

        logger.info("Success : trade with id {} updated, redirect to '/trade/list'", trade.getTradeId());

        return "redirect:/trade/list";
    }

    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {

        logger.info("Request : GET /trade/delete/{}", id);

        tradeService.deleteTradeById(id);

        logger.info("Success : trade with id {} deleted, redirect to '/trade/list'", id);

        return "redirect:/trade/list";
    }
}
