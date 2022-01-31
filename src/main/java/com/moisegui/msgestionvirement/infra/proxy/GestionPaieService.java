package com.moisegui.msgestionvirement.infra.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;
import java.util.List;

@FeignClient("gestion-paie-service")
public interface GestionPaieService {
    @GetMapping("/gestion-paie/api/v1/userEchelons/user/currentMontant/{userMatricule}")
    public BigDecimal findUserCurrentEchelonMontant(@PathVariable String userMatricule);

    @GetMapping("/gestion-paie/api/v1/userPrimes/user/currentMontant/{userMatricule}")
    public BigDecimal findUserCurrentPrimesMontant(@PathVariable String userMatricule);

    @GetMapping("/gestion-paie/api/v1/userEchelons/active/user/{userMatricule}")
    public String findActiveEchelonForUser(@PathVariable String userMatricule);

    @GetMapping("/gestion-paie/api/v1/userPrimes/active/user/{userMatricule}")
    public List<String> findActivePrimesForUser(@PathVariable String userMatricule);
}
