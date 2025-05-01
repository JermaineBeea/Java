// Even simpler server using Express framework
const express = require('express');
const path = require('path');
const app = express();
const PORT = 8080;

// Middleware to parse request body
app.use(express.text());

// Serve static files from current directory
app.use(express.static(path.join(__dirname)));

// Handle message sending
app.post('/send', (req, res) => {
    // Print the message to server console
    console.log('\nClient: ' + req.body);
    
    // Send response
    res.send('Message received');
});

// Start the server
app.listen(PORT, () => {
    console.log(`Express server running at http://localhost:${PORT}`);
    console.log('Open this address in your browser to access the chat');
});