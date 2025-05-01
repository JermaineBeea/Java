# How to Run the Node.js Chat Server

This is a much simpler approach using JavaScript and Node.js to create a web chat server. It's easier to set up and understand compared to the Java version.

## Prerequisites

You need to have Node.js installed on your computer. If you don't have it already, you can download it from [nodejs.org](https://nodejs.org/).

## Setup

1. Create a new folder for your project
2. Save these two files in that folder:
   - `server.js` - The server code
   - `index.html` - The web interface

## Running the Server

1. Open a command prompt or terminal
2. Navigate to your project folder
3. Run the server with:
   ```
   node server.js
   ```
4. You should see the following output:
   ```
   Server started on http://localhost:8080
   Open this address in your browser to access the chat
   ```

## Using the Chat

1. Open your web browser
2. Go to `http://localhost:8080`
3. You'll see a simple chat interface
4. Type a message and click "Send" or press Enter
5. The message will be sent to the server
6. Check your terminal/command prompt to see the message displayed on the server

## Advantages of the Node.js Approach

1. **Simplified Setup**: No compilation needed, just run with Node.js
2. **Single Language**: Both client and server code use JavaScript
3. **Built-in Modules**: Node.js has built-in HTTP server capabilities
4. **Easy to Extend**: Can be easily expanded to add more features
5. **Modern Web Development**: Uses current web development practices

## Stopping the Server

To stop the server, press `Ctrl+C` in the terminal or command prompt where the server is running.

## Troubleshooting

- **"Address already in use" Error**: Change the port number in `server.js` from 8080 to another number
- **"Cannot find module" Error**: Make sure you have Node.js installed properly
- **Connection Refused**: Ensure the server is running and you're using the correct URL