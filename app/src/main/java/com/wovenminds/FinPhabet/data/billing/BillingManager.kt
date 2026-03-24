package com.wovenminds.FinPhabet.data.billing

import android.app.Activity
import android.content.Context
import android.util.Log
import com.android.billingclient.api.*
import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.PurchasesUpdatedListener
import com.android.billingclient.api.BillingResult
import com.android.billingclient.api.Purchase
import com.android.billingclient.api.BillingClientStateListener
import com.android.billingclient.api.PendingPurchasesParams

class BillingManager (
    context: Context,
    private val onPurchaseSuccess:() -> Unit
) : PurchasesUpdatedListener
{
    private val productId = "finphabet_challenge"

    private val billingClient: BillingClient = BillingClient.newBuilder(context)
        .enablePendingPurchases(PendingPurchasesParams.newBuilder().enableOneTimeProducts().build())
        .setListener(this)
        .build()

    fun startConnection(onReady: () -> Unit)
    {
        billingClient.startConnection(object : BillingClientStateListener{
            override fun onBillingSetupFinished(billingResult: BillingResult) {
                if (billingResult.responseCode == BillingClient.BillingResponseCode.OK)
                {
                    onReady()
                }
                else
                {
                    //Handle Error
                    println("Billing setup failed: ${billingResult.debugMessage}")
                }
            }

            override fun onBillingServiceDisconnected() {
                //retry later
                println("Billing service disconnected. Will retry.")
            }
        })
    }
    fun launchPurchase(activity: Activity)
    {
        val productList = listOf(
            QueryProductDetailsParams.Product.newBuilder()
                .setProductId(productId)
                .setProductType(BillingClient.ProductType.INAPP).build()
        )

        val params = QueryProductDetailsParams.newBuilder()
            .setProductList(productList)
            .build()



        billingClient.queryProductDetailsAsync(params) { billingResult, queryProductDetailsResult ->
            if (billingResult.responseCode != BillingClient.BillingResponseCode.OK)
            {
                println("Error querying products: ${billingResult.debugMessage}")
                return@queryProductDetailsAsync
            }

            val productDetailsList = queryProductDetailsResult.productDetailsList

            if(productDetailsList.isNullOrEmpty())
            {
                println("productDetailsList is empty - check SKU, activation, and test account")
                return@queryProductDetailsAsync
            }

            val productDetails = productDetailsList[0]

            val billingFlowParams = BillingFlowParams.newBuilder()
                .setProductDetailsParamsList(
                    listOf(BillingFlowParams.ProductDetailsParams.newBuilder()
                        .setProductDetails(productDetails)
                        .build())
                ).build()
            billingClient.launchBillingFlow(activity, billingFlowParams)
        }
    }

    override fun onPurchasesUpdated(result: BillingResult, purchases: MutableList<Purchase>?) {
        if (result.responseCode == BillingClient.BillingResponseCode.OK && purchases != null)
        {
            purchases.forEach {
                purchase -> if (purchase.products.contains(productId)) {
                    if(!purchase.isAcknowledged)
                    {
                        val acknowledgeParams = AcknowledgePurchaseParams.newBuilder()
                            .setPurchaseToken(purchase.purchaseToken)
                            .build()

                        billingClient.acknowledgePurchase(acknowledgeParams){}

                        onPurchaseSuccess()
                    }
            }


            }
        }
    }

    fun restorePurchases()
    {
        billingClient.queryPurchasesAsync(QueryPurchasesParams.newBuilder()
            .setProductType(BillingClient.ProductType.INAPP)
            .build())
        {
            billingResult, purchases ->
            if (billingResult.responseCode == BillingClient.BillingResponseCode.OK)
            {
                purchases.forEach {
                    purchase -> if (purchase.products.contains(productId)) {
                        onPurchaseSuccess()
                    }
                }
            }
            else
            {
                println("Restore purchase failed: ${billingResult.debugMessage}")
            }
        }
    }
}
