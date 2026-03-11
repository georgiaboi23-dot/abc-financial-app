package com.wovenminds.FinPhabet.data.repository

import com.wovenminds.FinPhabet.data.model.ContentItem

class QuestionRepository {
    fun getContent(): List<ContentItem> {
        return listOf(
            ContentItem(
                id = "Q_A",
                letter = "A",
                word = "Alpha",
                definition = "A measure of an investment's performance relative to a benchmark."
            ),
            ContentItem(
                id = "Q_B",
                letter = "B",
                word = "Bear Market",
                definition = "A market condition where prices are falling."
            ),
            ContentItem(
                id = "Q_C",
                letter = "C",
                word = "Compound Interest",
                definition = "Interest calculated on the initial principal and accumulated interest."
            ),
            ContentItem(
                id = "Q_D",
                letter = "D",
                word = "Dividend",
                definition = "A payment distributed to shareholders from company profits."
            ),
            ContentItem(
                id = "Q_E",
                letter = "E",
                word = "Exchange",
                definition = "A marketplace where securities such as stocks are traded."
            ),
            ContentItem(
                id = "Q_F",
                letter = "F",
                word = "Futures",
                definition = "Contracts obligating the purchase or sale of an asset at a future date."
            ),
            ContentItem(
                id = "Q_G",
                letter = "G",
                word = "Growth",
                definition = "An increase in value, size, or profitability of an investment."
            ),
            ContentItem(
                id = "Q_H",
                letter = "H",
                word = "Hedge",
                definition = "An investment strategy used to offset potential losses."
            ),
            ContentItem(
                id = "Q_I",
                letter = "I",
                word = "Inflation",
                definition = "The rate at which the general level of prices for goods and services rises."
            ),
            ContentItem(
                id = "Q_J",
                letter = "J",
                word = "Junk Bond",
                definition = "A high-yield, high-risk security issued by a company with low credit rating."
            ),
            ContentItem(
                id = "Q_K",
                letter = "K",
                word = "KPI",
                definition = "Key Performance Indicator used to measure business performance."
            ),
            ContentItem(
                id = "Q_L",
                letter = "L",
                word = "Liquidity",
                definition = "The ability to quickly convert an asset into cash without significant loss."
            ),
            ContentItem(
                id = "Q_M",
                letter = "M",
                word = "Margin",
                definition = "Borrowed money used to purchase securities beyond cash holdings."
            ),
            ContentItem(
                id = "Q_N",
                letter = "N",
                word = "Nominal",
                definition = "The face value of an asset, bond, or currency without adjustment for inflation or market factors."
            ),
            ContentItem(
                id = "Q_O",
                letter = "O",
                word = "Option",
                definition = "A contract giving the buyer the right, but not the obligation, to buy or sell an asset."
            ),
            ContentItem(
                id = "Q_P",
                letter = "P",
                word = "Portfolio",
                definition = "A collection of financial investments held by an individual or institution."
            ),
            ContentItem(
                id = "Q_Q",
                letter = "Q",
                word = "Quota",
                definition = "A limited quantity of a product or service officially allowed to be produced or imported."
            ),
            ContentItem(
                id = "Q_R",
                letter = "R",
                word = "Revenue",
                definition = "Income generated from normal business operations."
            ),
            ContentItem(
                id = "Q_S",
                letter = "S",
                word = "Stock",
                definition = "A type of security representing ownership in a company."
            ),
            ContentItem(
                id = "Q_T",
                letter = "T",
                word = "Treasury",
                definition = "Government-issued debt securities used to fund public spending."
            ),
            ContentItem(
                id = "Q_U",
                letter = "U",
                word = "Underwriting",
                definition = "The process by which a bank or institution evaluates risk for a security or insurance."
            ),
            ContentItem(
                id = "Q_V",
                letter = "V",
                word = "Valuation",
                definition = "The process of determining the current worth of an asset or company."
            ),
            ContentItem(
                id = "Q_W",
                letter = "W",
                word = "Warrant",
                definition = "A derivative that gives the holder the right to purchase stock at a specific price."
            ),
            ContentItem(
                id = "Q_X",
                letter = "X",
                word = "X-efficiency",
                definition = "A measure of efficiency in which a firm minimizes costs given its resources."
            ),
            ContentItem(
                id = "Q_Y",
                letter = "Y",
                word = "Yield Curve",
                definition = "A graph showing the relationship between interest rates and the maturity of debt for a given borrower."
            ),
            ContentItem(
                id = "Q_Z",
                letter = "Z",
                word = "Zero-Coupon Bond",
                definition = "A bond that does not pay periodic interest but is sold at a discoount to its face value"
            )
        )
    }
}