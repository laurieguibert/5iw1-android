package apackage.thetvdb.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import apackage.thetvdb.LoginActivity;
import apackage.thetvdb.R;
import apackage.thetvdb.entity.Account;
import apackage.thetvdb.entity.ServiceResponse;
import apackage.thetvdb.service.AccountService;
import apackage.thetvdb.service.IAccountService;
import apackage.thetvdb.service.ResponseListener;


public class AccountFragment extends Fragment {

    private IAccountService accountService;

    private IAccountService getAccountService() {
        if(accountService == null) {
            accountService = new AccountService();
        }

        return accountService;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_account,container,false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getAccountService().getCredentials(new ResponseListener<Account>() {
            @Override
            public void onSuccess(ServiceResponse<Account> serviceResponse) {
                Account account = serviceResponse.getData();
                if(account == null) {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
