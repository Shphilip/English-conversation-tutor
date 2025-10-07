document.getElementById("loginForm").addEventListener("submit", function(e){
    e.preventDefault();

    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    if(!username || !password){
        document.getElementById("errorMessage").style.display = "block";
        return;
    }

    //Save the username and password in session storage
    sessionStorage.setItem("username", username);
    sessionStorage.setItem("password", password);

    //Hide the login popup wile the login is not realy implemented
    document.getElementById("loginPopup").style.display = "none";

});


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