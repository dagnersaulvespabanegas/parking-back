package com.daniela.Entity;


import java.util.Random;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "smartContracts")
public class SmartContract {
    @Id
    private Long smartId;    
    private String trxHash;


 
    public SmartContract() {
    	 this.smartId = new Random().nextLong();
    }


    public SmartContract( String trxHash) {
    	 this.smartId = new Random().nextLong();
    	 this.trxHash = trxHash;;
    }

    // Getters y Setters
    public Long getSmartId() {
        return smartId;
    }

    public void setSmartId(Long smartId) {
        this.smartId = smartId;
    }


	public String getTrxHash() {
		return trxHash;
	}


	public void setTrxHash(String trxHash) {
		this.trxHash = trxHash;
	}

}
