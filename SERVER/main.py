from fastapi import FastAPI

app = FastAPI()

@app.post("/usage")
def receive_usage(data: dict):
    print("Received:", data)
    return {"status": "ok"}
