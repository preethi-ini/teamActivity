import React, { useEffect, useState } from 'react'
import ChoiceService from './ChoiceService';

const GetChoice = () => {

  const [loading, setLoading] = useState(true); 
  const [choices, setChoices] = useState(null);
    
  useEffect(() => {
    const fetchData = async () =>{
        setLoading(true);
        try {
            const response = await ChoiceService.getChoice();
            setChoices(response.data);
        } catch(error) {
            setChoices("Error in fetching a response");
            console.log(error);
        }
        setLoading(false);
    };
    fetchData();
},
[])

  return (
    <div> Selected Choice is {choices} </div>
  )
}

export default GetChoice