import com.Shlomi.englishapp.English_conversation_tutor.conversation.AIMessage;
import com.Shlomi.englishapp.English_conversation_tutor.conversation.UserMessage;

class AIModel implements ChatModel{

    @Override
    public AIMessage getResponse(UserMessage userMessage){
        return new AIMessage();
    }

}
