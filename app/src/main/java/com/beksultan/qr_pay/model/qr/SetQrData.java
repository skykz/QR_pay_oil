package com.beksultan.qr_pay.model.qr;

public class SetQrData {

        public String balance;
        public String login;
        public String personId;
        public int departmentId;

        public SetQrData(String balance, String login, String personId,int departmentId) {
            this.balance = balance;
            this.login = login;
            this.personId = personId;
            this.departmentId = departmentId;
        }

}
