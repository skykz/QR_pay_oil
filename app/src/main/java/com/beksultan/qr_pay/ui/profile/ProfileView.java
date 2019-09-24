package com.beksultan.qr_pay.ui.profile;

import com.beksultan.qr_pay.model.user.User;
import com.beksultan.qr_pay.ui.base.BaseCategoryView;

public interface ProfileView extends BaseCategoryView{

    void onInit(User user);

    void onSuccessUpdateUser();

    void onLogOut();
}
