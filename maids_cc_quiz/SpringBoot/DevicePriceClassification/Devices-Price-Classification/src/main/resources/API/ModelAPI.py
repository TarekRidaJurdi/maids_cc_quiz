# Importing libraries
import pandas as pd
import seaborn as sns
import matplotlib.pyplot as plt
from sklearn.model_selection import train_test_split
from sklearn.metrics import confusion_matrix, classification_report,accuracy_score
from sklearn.preprocessing import MinMaxScaler,StandardScaler
from sklearn.ensemble import RandomForestClassifier, GradientBoostingClassifier, AdaBoostClassifier, ExtraTreesClassifier
from sklearn.svm import SVC
from sklearn.neighbors import KNeighborsClassifier
from sklearn.tree import DecisionTreeClassifier
from sklearn.naive_bayes import GaussianNB
from sklearn.linear_model import LogisticRegression
from sklearn.neural_network import MLPClassifier
from sklearn.pipeline import Pipeline
from sklearn.feature_selection import SelectKBest, f_classif
import joblib
from fastapi import FastAPI, HTTPException
from fastapi.middleware.cors import CORSMiddleware
import uvicorn
import pandas as pd

# Initialize FastAPI app
app = FastAPI()
# List of allowed origins (origins that can make cross-origin requests)
# Configure CORS
origins = [
    "http://localhost:8000",
    "http://127.0.0.1:8000",
    "null",  # You may need to add this domain if you use it in your application
]
app.add_middleware(
    CORSMiddleware,
    allow_origins=origins,
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)
# Reading test data
test_data = pd.read_csv('testdata.csv')
# Load the best model
best_model = joblib.load('best_model.pkl')

def predict_sample_by_id(data, model, features, sample_id):
    """
    Predicts a sample from the data based on ID using only the selected features.
    Args:
        data (pandas.DataFrame): The data to be predicted.
        model: The trained model.
        features (list): List of selected features.
        sample_id: The ID of the sample to predict.
    Returns:
        Prediction for the sample.
    """
    # Extracting the sample based on ID and selected features only
    sample = data[data['id'] == sample_id][features]
    
    # Predicting sample using the model
    prediction = model.predict(sample)
    
    return prediction

# Define the selected features
selected_features = ['battery_power', 'int_memory', 'mobile_wt', 'n_cores', 'px_height', 'px_width', 'ram', 'sc_h', 'sc_w', 'talk_time']

# Define mapping for class labels
class_labels = {
    0: 'low cost',
    1: 'medium cost',
    2: 'high cost',
    3: 'very high cost'
}

@app.post("/predict/{sample_id}")
async def predict_sample(sample_id: int):
    if sample_id not in test_data['id'].values:
        raise HTTPException(status_code=404, detail="Sample ID not found")
    
    prediction = predict_sample_by_id(test_data, best_model, selected_features, sample_id)
    predicted_class = class_labels[prediction[0]]
    
    return {"predicted_class": predicted_class}


if __name__ == "__main__":
    # Run the application using Uvicorn server
    uvicorn.run("ModelAPI:app", reload=True)
