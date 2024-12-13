const tf = require('@tensorflow/tfjs-node');
const express = require('express');
const bodyParser = require('body-parser');
const cors = require('cors');

const app = express();
app.use(cors());
app.use(bodyParser.json());

let model;

// Load the TensorFlow model
const loadModel = async () => {
    try {
        model = await tf.loadLayersModel('https://storage.googleapis.com/model_moodiometry/model.json');
        console.log('Model loaded successfully!');
    } catch (error) {
        console.error('Error loading model:', error);
    }
};

// Endpoint to handle predictions
app.post('/predict', async (req, res) => {
    try {
        const inputData = req.body.data; // Expecting a 2D array
        const inputTensor = tf.tensor(inputData);
        const predictions = model.predict(inputTensor).arraySync();
        res.json({ predictions });
    } catch (error) {
        res.status(400).json({ error: error.message });
    }
});

// Start the server
const PORT = process.env.PORT || 8080;
app.listen(PORT, async () => {
    console.log(`Server running on port ${PORT}`);
    await loadModel();
});
