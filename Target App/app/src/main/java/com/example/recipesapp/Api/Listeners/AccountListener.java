package com.example.recipesapp.Api.Listeners;

import com.example.recipesapp.Api.Models.Models.AccountDetails.Account;

import java.util.List;

public interface AccountListener {
    void didFetch(Account account, String message);
    void didError(String error);
}
