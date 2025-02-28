package com.qucoon.myhmo.viewmodel

import com.example.neptune.viewmodel.BaseViewModel
import com.qucoon.myhmo.database.PaperPrefs
import com.qucoon.myhmo.database.getStringPref
import com.qucoon.myhmo.model.request.confirmpaystack.PaystackConfirmationRequest
import com.qucoon.myhmo.model.request.enrolmentcomplete.enrolmentcompleteRequest
import com.qucoon.myhmo.model.request.enroluser.EnroluserRequest
import com.qucoon.myhmo.model.request.initpaystack.InitpaymentRequest
import com.qucoon.myhmo.model.request.newpasswordReset.NewpasswordResetRequest
import com.qucoon.myhmo.model.response.confirmpaystack.PaystackConfirmationResponse
import com.qucoon.myhmo.model.response.enrolmentcompleteResponse
import com.qucoon.myhmo.model.response.enroluser.EnroluserResponse
import com.qucoon.myhmo.model.response.initpayment.InitpaymentResponse
import com.qucoon.myhmo.repository.DashboardRepossitory
import com.qucoon.myhmo.repository.implDashboardRepossitory
import com.qucoon.myhmo.views.fragment.insidefrgments.dashoard.enrolment.BeneficiaryDetails
import com.qucoon.nibbs.utils.SingleLiveEvent


class DashboardViewModel(private val dashboardRepossitory: DashboardRepossitory,private val paperPrefs: PaperPrefs): BaseViewModel(){
    val initpaymentResponse = SingleLiveEvent<InitpaymentResponse>()
    val paystackConfirmationResponse = SingleLiveEvent<PaystackConfirmationResponse>()
    var enroluserResponse = SingleLiveEvent<EnroluserResponse>()
    val enrolmentcompleteResponse= SingleLiveEvent<enrolmentcompleteResponse>()


     fun enrolmentcomplete(address:String,dob:String,gender:String,image:String){
         val request = enrolmentcompleteRequest(address,dob,paperPrefs.getStringPref(PaperPrefs.CUSTOMERID),gender,image)
         apiRequest(request,dashboardRepossitory::enrolmentcomplete,enrolmentcompleteResponse,{it.responsemessage})
     }

    fun enrolusers( amount: String, category_type: String, duration: String, schedule_payment: String,
                    subscriber: String, subtype: String, user_package: String,subscriber_info: List<BeneficiaryDetails>){

        var request = EnroluserRequest(amount = amount,category_type = category_type,
            customer_email = paperPrefs.getStringPref(PaperPrefs.EMAIL),customerid = paperPrefs.getStringPref(PaperPrefs.CUSTOMERID),
        duration = duration,firstName = paperPrefs.getStringPref(PaperPrefs.FIRSTNAME), lastName= paperPrefs.getStringPref(PaperPrefs.LASTNAME),
        schedule_payment = schedule_payment,subscriber = subscriber,subtype = subtype,user_package = user_package, subscriber_info = subscriber_info)

        apiRequest(request,dashboardRepossitory::enrolUser,enroluserResponse,{it.responsemessage})
    }

     fun confirmPaystack(reference:String){
         var request = PaystackConfirmationRequest(email= paperPrefs.getStringPref(PaperPrefs.EMAIL), reference = reference)
         apiRequest(request,dashboardRepossitory::paystackConfirm,paystackConfirmationResponse,{it.responsemessage})
     }

    fun initPayment(amount: String,email:String){
        var request = InitpaymentRequest(amount= amount, email = email)
        apiRequest(request,dashboardRepossitory::initpayment,initpaymentResponse,{it.responsemessage})
    }
}