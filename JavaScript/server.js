// Import the built-in 'http' module
const http = require('http');
const fs = require('fs');
const path = require('path');

// Define the port
const PORT = 8080;

// Create the HTTP server
const server = http.createServer((req, res) => {
    // Get the request method and URL
    const method = req.method;
    const url = req.url;
    
    // Handle GET requests for the home page
    if (method === 'GET' && (url === '/' || url === '/index.html')) {
        // Read the HTML file
        fs.readFile(path.join(__dirname, 'index.html'), (err, data) => {
            if (err) {
                // If there's an error reading the file
                res.writeHead(500, { 'Content-Type': 'text/plain' });
                res.end('Error loading HTML file');
                return;
            }
            
            // Send the HTML file
            res.writeHead(200, { 'Content-Type': 'text/html' });
            res.end(data);
        });
    } 
    // Handle POST requests (messages from client)
    else if (method === 'POST' && url === '/send') {
        let body = '';
        
        // Get the data from the request body
        req.on('data', (chunk) => {
            body += chunk.toString();
        });
        
        // When all data is received
        req.on('end', () => {
            // Print the message to the console
            console.log('\nClient: ' + body);
            
            // Send a response
            res.writeHead(200, { 'Content-Type': 'text/plain' });
            res.end('Message received');
        });
    } 
    // Handle all other requests
    else {
        res.writeHead(404, { 'Content-Type': 'text/plain' });
        res.end('Not Found');
    }
});

// Start the server
server.listen(PORT, () => {
    console.log(`Server started on http://localhost:${PORT}`);
    console.log('Open this address in your browser to access the chat');
});