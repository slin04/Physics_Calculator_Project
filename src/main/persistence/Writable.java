package persistence;

import org.json.JSONObject;

// Represents an object that be written as a JSONObject
// Code influenced by the JsonSerializationDemo: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public interface Writable {
    // EFFECTS: returns this as a JSON object
    JSONObject toJson();
}
