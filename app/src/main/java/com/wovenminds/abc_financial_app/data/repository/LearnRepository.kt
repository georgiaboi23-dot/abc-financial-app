package com.wovenminds.abc_financial_app.data.repository
import com.wovenminds.abc_financial_app.data.model.LearnItem

class LearnRepository
{
    fun getLearnItems():List<LearnItem>
    {
        return listOf(
            LearnItem(
                id="A",
                letter = "A",
                word = "Asset",
                definition = "Something of value that you own."
            ),
            LearnItem(
                id="B",
                letter = "B",
                word="Budget",
                definition = "A plan for managing income and expenses."
            ),
            LearnItem(
                id="C",
                letter = "C",
                word = "Credit",
                definition = "Ability to borrow money with agreement to repay."
            ),
            LearnItem(
                id = "D",
                letter = "D",
                word = "Debt",
                definition = "Money owed to another person or institution."
            ),
            LearnItem(
                id = "E",
                letter="E",
                word = "Equity",
                definition = "Ownership value in an asset after debts are subtracted."
            ),
            LearnItem(
                id = "F",
                letter = "F",
                word = "Finance",
                definition = "Management of money and investments."
            ),
            LearnItem(
                id="G",
                letter = "G",
                word = "Gross Income",
                definition = "Total income earned before deductions."
            ),
            LearnItem(
                id = "H",
                letter = "H",
                word = "Holding",
                definition = "An investment owned in a portfolio."
            ),
            LearnItem(
                id="I",
                letter = "I",
                word = "Income",
                definition = "Money received from work, investments, or business."
            ),
            LearnItem(
                id = "J",
                letter = "J",
                word = "Joint Account",
                definition = "A financial account shared by two or more people."
            ),
            LearnItem(
                id = "K",
                letter = "K",
                word = "Knowledge",
                definition = "Understanding gained through learning and experience."
            ),
            LearnItem(
                id = "L",
                letter = "L",
                word = "Liability",
                definition = "A financial obligation or debt."
            ),
            LearnItem(
                id = "M",
                letter = "M",
                word = "Market",
                definition = "A system where buyers and sellers trade assets."
            ),
            LearnItem(
                id = "N",
                letter = "N",
                word = "Net Income",
                definition = "Income remaining after expenses and taxes."
            ),
            LearnItem(
                id = "O",
                letter = "O",
                word = "Opportunity Cost",
                definition = "The value of the next best alternative given up."
            ),
            LearnItem(
                id = "P",
                letter = "P",
                word = "Profit",
                definition = "Financial gain after subtracting costs."
            ),
            LearnItem(
                id = "Q",
                letter = "Q",
                word = "Quarter",
                definition = "A three-month financial reporting period."
            ),
            LearnItem(
                id = "R",
                letter = "R",
                word = "Risk",
                definition = "The possibility of losing money on an investment."
            ),
            LearnItem(
                id = "S",
                letter="S",
                word = "Savings",
                definition = "Money set aside for future use."
            ),
            LearnItem(
                id = "T",
                letter = "T",
                word = "Transaction",
                definition = "An exchange of money for goods, services, or assets."
            ),
            LearnItem(
                id="U",
                letter = "U",
                word = "Utility",
                definition = "The usefulness or value of a good or service."
            ),
            LearnItem(
                id = "V",
                letter = "V",
                word = "Volatility",
                definition = "The rate at which the price of an asset changes."
            ),
            LearnItem(
                id = "W",
                letter = "W",
                word = "Wealth",
                definition = "Accumulated valuable assets and resources."
            ),
            LearnItem(
                id = "X",
                letter = "X",
                word = "Xenocurrency",
                definition = "A currency used outside its country of origin or traded in international markets."
            ),
            LearnItem(
                id = "Y",
                letter = "Y",
                word = "Yield",
                definition = "The income generated from an investment."
            ),
            LearnItem(
                id = "Z",
                letter = "Z",
                word = "Zero-Based Budgeting",
                definition = "A budgeting method where every dollar is assigned a purpose."
            )

        )
    }
}
