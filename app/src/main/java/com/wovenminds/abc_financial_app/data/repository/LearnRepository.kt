package com.wovenminds.abc_financial_app.data.repository
import com.wovenminds.abc_financial_app.data.model.ContentItem

class LearnRepository
{
    fun getLearnItems():List<ContentItem>
    {
        return listOf(
            ContentItem(
                id="A",
                letter = "A",
                word = "Asset",
                definition = "Something of value that you own."
            ),
            ContentItem(
                id="B",
                letter = "B",
                word="Budget",
                definition = "A plan for managing income and expenses."
            ),
            ContentItem(
                id="C",
                letter = "C",
                word = "Credit",
                definition = "Ability to borrow money with agreement to repay."
            ),
            ContentItem(
                id = "D",
                letter = "D",
                word = "Debt",
                definition = "Money owed to another person or institution."
            ),
            ContentItem(
                id = "E",
                letter="E",
                word = "Equity",
                definition = "Ownership value in an asset after debts are subtracted."
            ),
            ContentItem(
                id = "F",
                letter = "F",
                word = "Finance",
                definition = "Management of money and investments."
            ),
            ContentItem(
                id="G",
                letter = "G",
                word = "Gross Income",
                definition = "Total income earned before deductions."
            ),
            ContentItem(
                id = "H",
                letter = "H",
                word = "Holding",
                definition = "An investment owned in a portfolio."
            ),
            ContentItem(
                id="I",
                letter = "I",
                word = "Income",
                definition = "Money received from work, investments, or business."
            ),
            ContentItem(
                id = "J",
                letter = "J",
                word = "Joint Account",
                definition = "A financial account shared by two or more people."
            ),
            ContentItem(
                id = "K",
                letter = "K",
                word = "Knowledge",
                definition = "Understanding gained through learning and experience."
            ),
            ContentItem(
                id = "L",
                letter = "L",
                word = "Liability",
                definition = "A financial obligation or debt."
            ),
            ContentItem(
                id = "M",
                letter = "M",
                word = "Market",
                definition = "A system where buyers and sellers trade assets."
            ),
            ContentItem(
                id = "N",
                letter = "N",
                word = "Net Income",
                definition = "Income remaining after expenses and taxes."
            ),
            ContentItem(
                id = "O",
                letter = "O",
                word = "Opportunity Cost",
                definition = "The value of the next best alternative given up."
            ),
            ContentItem(
                id = "P",
                letter = "P",
                word = "Profit",
                definition = "Financial gain after subtracting costs."
            ),
            ContentItem(
                id = "Q",
                letter = "Q",
                word = "Quarter",
                definition = "A three-month financial reporting period."
            ),
            ContentItem(
                id = "R",
                letter = "R",
                word = "Risk",
                definition = "The possibility of losing money on an investment."
            ),
            ContentItem(
                id = "S",
                letter="S",
                word = "Savings",
                definition = "Money set aside for future use."
            ),
            ContentItem(
                id = "T",
                letter = "T",
                word = "Transaction",
                definition = "An exchange of money for goods, services, or assets."
            ),
            ContentItem(
                id="U",
                letter = "U",
                word = "Utility",
                definition = "The usefulness or value of a good or service."
            ),
            ContentItem(
                id = "V",
                letter = "V",
                word = "Volatility",
                definition = "The rate at which the price of an asset changes."
            ),
            ContentItem(
                id = "W",
                letter = "W",
                word = "Wealth",
                definition = "Accumulated valuable assets and resources."
            ),
            ContentItem(
                id = "X",
                letter = "X",
                word = "Xenocurrency",
                definition = "A currency used outside its country of origin or traded in international markets."
            ),
            ContentItem(
                id = "Y",
                letter = "Y",
                word = "Yield",
                definition = "The income generated from an investment."
            ),
            ContentItem(
                id = "Z",
                letter = "Z",
                word = "Zero-Based Budgeting",
                definition = "A budgeting method where every dollar is assigned a purpose."
            )

        )
    }
}
