package cphbusiness.groupone.exceptions;

import cphbusiness.groupone.system.ExceptionLogger;

public class NoResultException extends Exception{
    public NoResultException(String msg){
        super(msg);
        ExceptionLogger.log(msg);
    }

    public NoResultException(String msg, Exception e){
        super(msg);
        ExceptionLogger.log(msg+" "+e.toString());
    }
}
