//Connect microphone button to speech recognition function
document.getElementById('micButton').addEventListener('click', function() {
    speechRecognition();
});

/**
 * Updates the user text display area with the provided text
 * @param {string} textToAdd - The text to display in the user text area
 */
function addTextToUserScreen(textToAdd){
    document.getElementById('userTextDisplay').textContent = textToAdd;
}


function addTextToChatBotScreen(textToAdd){
    document.getElementById('avatarTextDisplay').textContent = textToAdd;
}