package com.example.luai.luai;

import java.util.ArrayList;

public class GenExample<FOFO> {
   private FOFO fofo;
    public GenExample(FOFO fofo) {

    }

    public int getSize(){
        try{

            if (fofo instanceof ArrayList){
                return ((ArrayList) fofo).size();
            }else if (fofo instanceof String){
                return 0;
            }else {
                return -1;
            }


        }catch (Exception e){
            return -1;
        }
    }
}
