
// let aiModelSpeak = false; 
let silenceTimer = null; 
let interim_transcript = "";
let final_transcript = "";
let recognition = null; // Make recognition global


/**
 * Initializes and starts Web Speech API for voice-to-text conversion
 * Handles both interim results (live) and final results (completed speech).
 * The main function for handling speech recognition.
 */
function speechRecognition(){
    if (("webkitSpeechRecognition" in window)){ // && !aiModelSpeak) { //Check if browser supports Web Speech API and AI model is not speaking
        if (!recognition) {
            recognition = new webkitSpeechRecognition();
            recognition.continuous = true; //TODO is it necessary? Do we need continuous conversation?
            recognition.interimResults = true; // Get partial while the user is still speaking, not just the final text
            recognition.onresult = handleSpeechResult; //Assign the speech result handler function
        }
        recognition.start();
    }
}

 
/**
 * Handles speech recognition results from Web Speech API
 * Processes both interim results (live) and final results (completed speech)
 * @param {SpeechRecognitionEvent} event - The speech recognition event
 */


function handleSpeechResult(event) {

    for (let i = event.resultIndex; i < event.results.length; i++) {
        if (event.results[i].isFinal) {
            final_transcript += event.results[i][0].transcript;
            addTextToUserScreen(final_transcript);
        } 
        
        else {
            interim_transcript += event.results[i][0].transcript;
            addTextToUserScreen(interim_transcript);
            interim_transcript = "";
        }
    }

    if(silenceTimer) {
        clearTimeout(silenceTimer);
    }

    silenceTimer = setTimeout(() => {
        console.log("User stopped speaking, sending to AI model: " + final_transcript);
        postRequest(final_transcript);
        final_transcript = "";
    }, 3000);

}
    
function speakText(text) {
    aiModelSpeak = true;
    // Stop recognition if running
    if (recognition && recognition.stop) {
        try { recognition.stop(); } catch (e) {}
    }
    let speech  = new SpeechSynthesisUtterance(text);
    speech.lang = 'en-US'; // Set language  
    speech.volume = 1; // Set volume (0 to 1)
    speech.rate = 1.6; // Set speed (0.1 to 10)
    speech.pitch = 1; // Set pitch (0 to 2)
    speechSynthesis.speak(speech);

    speech.onend = () => {
        // aiModelSpeak = false;
        // Restart recognition after AI finishes speaking
        setTimeout(() => {
            speechRecognition();
        }, 500); // Small delay to avoid overlap
    };
}

function postRequest(userText){
    const username = sessionStorage.getItem("username");
    const password = sessionStorage.getItem("password");
    
    console.log("Username:", username);
    console.log("Password:", password);

    fetch("/api/conversation", {
        method: "POST",
        headers: {"Content-Type": "application/json"}, //application = General type (Not text or image), type/subtype
        body: JSON.stringify({ 
            username: username,
            password: password,
            message: userText}) //Convert text to JSON
    })
    .then(res => res.json())
    .then(data => {
        addTextToChatBotScreen(data.message);
        speakText(data.message);
    });
}



