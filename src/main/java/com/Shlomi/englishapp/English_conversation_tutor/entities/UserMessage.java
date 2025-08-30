package com.Shlomi.englishapp.English_conversation_tutor.entities;

public class UserMessage implements MessageInterface{
    private String massage;
    private static Boolean isFirsMassage = true;

    @Override
    public String getMessage() {
        return this.massage;
    }

    @Override
    public void setMessage(String massage) {
        this.massage = massage;
        isFirsMassage = false;
    }
    @Override
    public boolean isFirstMassage(){
        return isFirsMassage;
    }

}
