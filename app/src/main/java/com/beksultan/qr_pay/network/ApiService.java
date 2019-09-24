package com.beksultan.qr_pay.network;

import com.beksultan.qr_pay.model.balance.Balance;
import com.beksultan.qr_pay.model.balance.BalanceParam;
import com.beksultan.qr_pay.model.business.director.detail.DepartmentItem;
import com.beksultan.qr_pay.model.business.director.param.SetBalanceParam;
import com.beksultan.qr_pay.model.business.director.param.DepartmentParam;
import com.beksultan.qr_pay.model.business.director.Company;
import com.beksultan.qr_pay.model.business.director.addDepartment.SearchHeadResponse;
import com.beksultan.qr_pay.model.business.director.param.CreateParam;
import com.beksultan.qr_pay.model.business.director.param.DeleteParam;
import com.beksultan.qr_pay.model.business.director.param.DetailDepartmentParam;
import com.beksultan.qr_pay.model.business.director.param.HeadParam;
import com.beksultan.qr_pay.model.business.head.add.SearchStaff;
import com.beksultan.qr_pay.model.business.head.param.AddStaffParam;
import com.beksultan.qr_pay.model.business.head.param.DeleteStaffParam;
import com.beksultan.qr_pay.model.business.head.param.DepartmentHeadParam;
import com.beksultan.qr_pay.model.business.director.param.UpdateParam;
import com.beksultan.qr_pay.model.business.head.DepartmentHead;
import com.beksultan.qr_pay.model.business.head.param.SearchStaffParam;
import com.beksultan.qr_pay.model.qr.SetQrData;
import com.beksultan.qr_pay.model.transaction.TransactionResponse;
import com.beksultan.qr_pay.model.transaction.param.AllParam;
import com.beksultan.qr_pay.model.transaction.param.UserAndCompanyParam;
import com.beksultan.qr_pay.model.user.User;
import com.beksultan.qr_pay.model.user.param.AuthParam;
import com.beksultan.qr_pay.model.user.param.RegistrationParam;
import com.beksultan.qr_pay.model.user.param.UpdateUserParam;
import java.util.List;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    // ------------------------------ Balance ---------------------------------- //
    @POST("getbalances")
    Single<Balance> getBalance(@Body BalanceParam param);

    // ------------------------------------------------------------------------------------------- //

    // ------------------------------ Authorization and Profile ---------------------------------- //

    @POST("getperson")
    Call<User> authorization(@Body AuthParam authParam);

    @POST("update-person")
    Single<Boolean> updateUser(@Body UpdateUserParam updateUserParam);

    // ------------------------------------------------------------------------------------------- //

    // ------------------------------ Registration ---------------------------------- //

    @POST("mobileregistration")
    Single<User> registration(@Body RegistrationParam registrationParam);

    // ------------------------------------------------------------------------------------------- //

    // ------------------------------ Transactions ----------------------------------------------- //

    @POST("getpersonaltransactions")
    Single<TransactionResponse> getUserTransactionList(@Body UserAndCompanyParam param);

    @POST("getcooptransactions")
    Single<TransactionResponse> getCompanyTransactionList(@Body UserAndCompanyParam param);

    @POST("gettransactions")
    Single<TransactionResponse> getAllTransactionList(@Body AllParam param);

    // ------------------------------------------------------------------------------------------- //

    // ------------------------------ Business(Director) ----------------------------------------- //

    @POST("b2b-director")
    Single<Company> getDepartmentList(@Body DepartmentParam param);

    // ------------------------------ Business(Search head) -------------------------------------- //

    @POST("searchhead")
    Single<SearchHeadResponse> getHead(@Body HeadParam param);

    // ------------------------------ Business(create-department) -------------------------------- //

    @POST("create-department")
    Single<String> createDepartment(@Body CreateParam param);

    // ------------------------------ Business(delete-department) -------------------------------- //

    @POST("delete-department")
    Single<String> deleteDepartment(@Body DeleteParam param);

    // ------------------------------ Business(update-department) -------------------------------- //

    @POST("update-department")
    Single<String> updateDepartment(@Body UpdateParam param);

    // ------------------------------ Business(get-department) -------------------------------- //

    @POST("getdepartment")
    Single<DepartmentItem> getDepartmentStaff(@Body DetailDepartmentParam param);

    // ------------------------------------------------------------------------------------------- //

    // ------------------------------ Business(Head) --------------------------------------------- //

    @POST("b2b-head")
    Single<DepartmentHead> getHead(@Body DepartmentHeadParam param);

    // ------------------------------ Business(Head - delete staff) ------------------------------ //

    @POST("deletecooperator")
    Single<String> deleteStaff(@Body DeleteStaffParam param);

    // ------------------------------ Business(Head - search staff) ------------------------------ //

    @POST("searchperson")
    Single<SearchStaff> searchStaff(@Body SearchStaffParam param);

    // ------------------------------ Business(Head - search staff) ------------------------------ //

    @POST("addcooperator")
    Single<String> addStaff(@Body AddStaffParam param);

    // ------------------------------------------------------------------------------------------- //

    @POST("setbalance")
    Single<String> setBalance(@Body List<SetBalanceParam> params);


    @POST("user-payment")
    Call<String> setQrData(@Body SetQrData param);

}
