import React, { useState } from "react";

import ChoiceService from "./ChoiceService";
const AddNewChoice = () => {
  const [choice, setChoice] = useState({
    employeeId: null,
    choice: "",
  });

  const [data, setData] = useState(null);

  const handleChange = (e) => {
        const value = e.target.value;
    setChoice({ ...choice, [e.target.name]: value });
  };
  const validateChoice = (choice) => {
    if (choice.employeeId < 1 || choice.employeeId > 10000) {
      setData(
        "Please enter a valid employee Id. Employee Id must be between 1-10000"
      );
      return false;
    }

    if (choice.choice == null || choice.choice == "") {
      setData("Please enter a valid choice");
      return false;
    }
    return true;
  };

  const savechoice = (event) => {
    event.preventDefault();

    if (!validateChoice(choice)) return;

    ChoiceService.saveChoice(choice)
      .then((response) => {
        setData(response.data);
        console.log("response-" + JSON.stringify(response));
      })
      .catch((response) => {
        if ((response.status = "400")) {
          setData(
            "Please enter valid values. Employee Id must be between 1-1000 and a valid choice not more than 100 chars"
          );
        }
        setChoice({
          employeeId: "",
          choice: "",
        });
        console.log("response" + JSON.stringify(response));
      });
  };
  const reset = (e) => {
    e.preventDefault();
    setChoice({
      employeeId: "",
      choice: "",
    });
  };

  const clearError = (e) => {
    setData(null);
  };

  return (
    <div className="flex max-w-2xl mx-auto shadow border-b">
    <div style={{ color: "red" }}>{data}</div>
      <div className="px-8 py-8 block">
        <div className="font-thin tracking-wider left">
          <h1>Add New choice</h1>
        </div>
        <table>
          <tr>
            <td>
              <label className="block text-gray-600 text-sm font-normal ">
                {" "}
                Employee Id
              </label>
            </td>
            <td>
              <input
                type="number"
                name="employeeId"
                onChange={(e) => handleChange(e)}
                value={choice.employeeId}
                className="h-10 w-96 border mt-2 px-2 py-2"
              ></input>
            </td>
          </tr>
          <tr>
            <td>
              <label className="block text-gray-600 text-sm font-normal ">
                {" "}
                Choice of Restaurant
              </label>
            </td>
            <td>
              {" "}
              <input
                type="text"
                name="choice"
                onChange={(e) => handleChange(e)}
                value={choice.choice}
                className="h-10 w-96 border mt-2 px-2 py-2"
              ></input>
            </td>
          </tr>
        </table>
        <br />
        <div>
          <button onClick={savechoice} onMouseOver={clearError}>
            Save
          </button>
          <button onClick={reset}>Clear</button>
        </div>
      </div>
    </div>
  );
};

export default AddNewChoice;
