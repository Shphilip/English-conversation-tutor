

/**
 * Initializes and starts Web Speech API for voice-to-text conversion
 * Handles both interim results (live) and final results (completed speech)
 */
function speechRcognition(){
    if (('webkitSpeechRecognition' in window)) {
        let recognition = new webkitSpeechRecognition();
        recognition.continuous = true; //TODO is it necessary? Do we need continuous conversation?
        recognition.interimResults = true; // Get partial while the user is still speaking, not just the final text

        recognition.onresult = handleSpeechResult; //Assign the speech result handler function
        recognition.start(); // Start listening after all the settings up
    }
}



/**
 * Handles speech recognition results from Web Speech API
 * Processes both interim results (live) and final results (completed speech)
 * @param {SpeechRecognitionEvent} event - The speech recognition event
 */
function handleSpeechResult(event) {
    let interim_transcript = "";
    let final_transcript = "";

    for (let i = event.resultIndex; i < event.results.length; i++) {
        if (event.results[i].isFinal) { //User speech is final
            final_transcript += event.results[i][0].transcript;
            addTextToUserScreen(final_transcript);
            // console.log("Final massage: ", final_transcript);
            postRequest(final_transcript);
        } else { //Show live updates
            interim_transcript += event.results[i][0].transcript;
            addTextToUserScreen(interim_transcript);
        }
    }
}

function postRequest(userText){
    fetch("/api/conversation", {
        method: "POST",
        headers: {"Content-Type": "application/json"}, //application = General type (Not text or image), type/subtype
        body: JSON.stringify({ message: userText }) //Convert text to JSON
    })
    .then(res => res.json())
    .then(data => {
        addTextToChatBotScreen(data.message);    });
    // .catch(error => {
    //     console.error('Error:', error);
    // });
}



