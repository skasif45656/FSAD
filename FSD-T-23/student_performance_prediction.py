"""
Student Performance Prediction using Linear Regression

Requirements covered:
- pandas, numpy, scikit-learn
- create/load dataset
- preprocessing
- model training
- evaluation (MSE, R2)
- clear printed results
"""

import numpy as np
import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.compose import ColumnTransformer
from sklearn.pipeline import Pipeline
from sklearn.preprocessing import OneHotEncoder, StandardScaler
from sklearn.impute import SimpleImputer
from sklearn.linear_model import LinearRegression
from sklearn.metrics import mean_squared_error, r2_score


def create_sample_data(n_samples: int = 200, random_state: int = 42) -> pd.DataFrame:
    """
    Create a synthetic student dataset.
    Target variable: final_score
    """
    rng = np.random.default_rng(seed=random_state)

    study_hours = rng.normal(loc=5.0, scale=1.5, size=n_samples).clip(0, 12)
    attendance = rng.normal(loc=80, scale=12, size=n_samples).clip(40, 100)
    previous_score = rng.normal(loc=70, scale=10, size=n_samples).clip(30, 100)
    assignments_completed = rng.integers(low=0, high=11, size=n_samples)
    internet_access = rng.choice(["yes", "no"], size=n_samples, p=[0.85, 0.15])

    # Build target with some noise
    noise = rng.normal(loc=0, scale=5, size=n_samples)
    final_score = (
        4.5 * study_hours
        + 0.25 * attendance
        + 0.35 * previous_score
        + 1.2 * assignments_completed
        + np.where(internet_access == "yes", 2.0, -2.0)
        + noise
    ).clip(0, 100)

    df = pd.DataFrame(
        {
            "study_hours": study_hours,
            "attendance": attendance,
            "previous_score": previous_score,
            "assignments_completed": assignments_completed,
            "internet_access": internet_access,
            "final_score": final_score,
        }
    )

    # Inject a few missing values to demonstrate preprocessing
    missing_idx = rng.choice(df.index, size=10, replace=False)
    df.loc[missing_idx[:5], "attendance"] = np.nan
    df.loc[missing_idx[5:], "internet_access"] = np.nan

    return df


def main():
    # -----------------------------
    # 1) Load dataset (or create sample data)
    # -----------------------------
    # If you have a CSV, replace this with:
    # df = pd.read_csv("students.csv")
    df = create_sample_data()

    print("Dataset Preview:")
    print(df.head(), "\n")
    print(f"Dataset shape: {df.shape}\n")

    # -----------------------------
    # 2) Define features and target
    # -----------------------------
    target_col = "final_score"
    X = df.drop(columns=[target_col])
    y = df[target_col]

    # Separate numeric and categorical columns
    numeric_features = X.select_dtypes(include=["int64", "float64"]).columns.tolist()
    categorical_features = X.select_dtypes(include=["object"]).columns.tolist()

    # -----------------------------
    # 3) Data preprocessing pipeline
    # -----------------------------
    numeric_transformer = Pipeline(
        steps=[
            ("imputer", SimpleImputer(strategy="median")),  # handle missing numeric values
            ("scaler", StandardScaler()),  # scale numeric features
        ]
    )

    categorical_transformer = Pipeline(
        steps=[
            ("imputer", SimpleImputer(strategy="most_frequent")),  # handle missing categorical values
            ("onehot", OneHotEncoder(handle_unknown="ignore")),    # encode categories
        ]
    )

    preprocessor = ColumnTransformer(
        transformers=[
            ("num", numeric_transformer, numeric_features),
            ("cat", categorical_transformer, categorical_features),
        ]
    )

    # -----------------------------
    # 4) Train-test split
    # -----------------------------
    X_train, X_test, y_train, y_test = train_test_split(
        X, y, test_size=0.2, random_state=42
    )

    # -----------------------------
    # 5) Model pipeline (preprocessing + linear regression)
    # -----------------------------
    model = Pipeline(
        steps=[
            ("preprocessor", preprocessor),
            ("regressor", LinearRegression()),
        ]
    )

    # Train model
    model.fit(X_train, y_train)

    # -----------------------------
    # 6) Prediction and evaluation
    # -----------------------------
    y_pred = model.predict(X_test)

    mse = mean_squared_error(y_test, y_pred)
    r2 = r2_score(y_test, y_pred)

    # -----------------------------
    # 7) Print results clearly
    # -----------------------------
    print("Model Evaluation Results")
    print("-" * 30)
    print(f"Mean Squared Error (MSE): {mse:.4f}")
    print(f"R2 Score: {r2:.4f}\n")

    print("Sample Predictions (Actual vs Predicted):")
    results_df = pd.DataFrame(
        {
            "Actual": y_test.values[:10],
            "Predicted": y_pred[:10],
        }
    )
    print(results_df.to_string(index=False))


if __name__ == "__main__":
    main()
