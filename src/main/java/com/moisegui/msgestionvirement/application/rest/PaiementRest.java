package com.moisegui.msgestionvirement.application.rest;

import com.moisegui.msgestionvirement.application.dto.PaiementDto;
import com.moisegui.msgestionvirement.domain.core.Result;
import com.moisegui.msgestionvirement.domain.pojo.Virement;
import com.moisegui.msgestionvirement.domain.process.virement.PaiementProcess;
import com.moisegui.msgestionvirement.domain.process.virement.PayerProcessInput;
import com.moisegui.msgestionvirement.infra.facade.VirementInfra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/virements")
public class PaiementRest {
    @Autowired
    PaiementProcess paiementProcess;

    @Autowired
    VirementInfra virementInfra;

    @GetMapping("/")
    public List<Virement> findAll(){
        return virementInfra.findAll();
    }

    @GetMapping("/ref/{ref}")
    public Virement findByRef(@PathVariable String ref){
        return virementInfra.findByRef(ref);
    }

    @PostMapping("/")
    public Result payer(@RequestBody PaiementDto paiementDto) {
        return paiementProcess.execute(new PayerProcessInput(paiementDto.getRef(), paiementDto.getLibelle(), paiementDto.getUserMatricules()));
    }
}
