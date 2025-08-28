package com.Shlomi.englishapp.English_conversation_tutor.conversation;

public class UserMessage implements Message{
    private String massage;
    private static Boolean isFirsMassage = true;

    @Override
    public String getMassage() {
        return this.massage;
    }

    @Override
    public void setMassage(String massage) {
        this.massage = massage;
        isFirsMassage = false;
    }
    @Override
    public boolean isFirstMassage(){
        return isFirsMassage;
    }

}
