import logo from './logo.svg';
import './App.css';
import GetChoice from './components/GetChoice';
import AddNewChoice from './components/AddNewChoice';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
function App() {
  return (
    <div className="App">
      <header className="App-header">
      <h1>DineDecide: Your Team's Lunch Picker!!</h1>
      <p>
      Here's how it works:
      <p>
      Submit Your Preference: Every team member can access our web page and input their preferred restaurant.
      </p>
      Get a Suggestion: At any time, users can request a recommendation. The application will then randomly select a restaurant from the list of submitted choices, ensuring fairness and variety.
      With DineDecide, gone are the days of lengthy debates and indecision. Let's make lunch time fun and spontaneous again!
      </p>
     
      <div><a href='/AddNewChoice'>Add new choice</a></div>
      <div><a href='/GetChoice'>Get a Choice</a></div>
      
    
      <BrowserRouter>
        <Routes>     
        <Route path='/GetChoice' element={<GetChoice />}></Route>
        <Route path='/AddNewChoice' element={<AddNewChoice />}></Route>
      </Routes>
    </BrowserRouter>
    </header>
      
      
    </div>
  );
}

export default App;
