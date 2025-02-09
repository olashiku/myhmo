package com.qucoon.myhmo.views.fragment.insidefrgments.dashoard.enrolment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.lifecycle.Observer
import com.example.neptune.utils.argument
import com.example.neptune.utils.stripAmount
import com.qucoon.myhmo.R
import com.qucoon.myhmo.database.PaperPrefs
import com.qucoon.myhmo.database.getStringPref
import com.qucoon.myhmo.database.savePref
import com.qucoon.myhmo.viewmodel.DashboardViewModel
import com.qucoon.royalexchange.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_paystack.*
import org.koin.android.viewmodel.ext.android.viewModel

class PaystackFragment : BaseFragment() {
    val dashboardViewModel: DashboardViewModel by viewModel()
    val paystackurl: String by argument("paystackurl")
    val packagee:String  by argument("package")
    val duration:String  by argument("duration")
    val subscriber:String  by argument("subscriber")
    val amount:String  by argument("amount")
    val type:String by argument("type")
    val subtype:String by argument("subtype")
    val reference:String by argument("reference")
    val schedulapayment:String by argument("schedulapayment")
    val subscriber_info:List<BeneficiaryDetails> by argument("subscriber_info")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_paystack, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initWebview()
        initonClick()
        initViewModel()

    }

     fun initonClick(){
         backB.setOnClickListener {
             mFragmentNavigation.popFragment()
         }
     }

    fun initViewModel(){
        setUpObservers(dashboardViewModel)

        dashboardViewModel.paystackConfirmationResponse.observe(viewLifecycleOwner, Observer {

            paperPrefs.savePref(PaperPrefs.CATEGORYTYPE,type)
            paperPrefs.savePref(PaperPrefs.DAYSLEFT,getDaysLeft(duration))
            paperPrefs.savePref(PaperPrefs.DURATION,duration)

            dashboardViewModel.enrolusers(amount.stripAmount().replace(".00","00"),type,duration,schedulapayment,
                subscriber,subtype,type,subscriber_info)

        })

        dashboardViewModel.enroluserResponse.observe(viewLifecycleOwner, Observer {

            mFragmentNavigation.pushFragment(UploadPictureFragment())

        })

    }

     fun getDaysLeft(datetype:String):String{
         var number=""
         when(datetype){
             "Annually"->{
                 number="365"
             }
             "Quaterly"->{
                 number="90"
             }
             "Monthly"->{
                 number= "30"
             }
             else->{}
         }
          return number
     }

     fun initWebview(){
         webView.loadUrl(paystackurl)
         webView.settings.javaScriptEnabled = true
         webView.settings.javaScriptCanOpenWindowsAutomatically = true
         val settings = webView.settings
         settings.domStorageEnabled = true


         webView.webViewClient = object : WebViewClient() {
             override fun onPageFinished(view: WebView, url: String) {
                 println(url)
                 if(url.toLowerCase().contains("myhmo")){

                     dashboardViewModel.confirmPaystack(reference)



                 }

             }
         }


     }
}