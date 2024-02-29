package cphbusiness.groupone.exceptions;

import cphbusiness.groupone.system.Logger;

public class NoResultException extends Exception{
    public NoResultException(String msg){
        super(msg);
        Logger.exceptionLog(msg);
    }

    public NoResultException(String msg, Exception e){
        super(msg);
        Logger.exceptionLog(msg+" "+e.toString());
    }
}
