import axios from "axios";

const ADD_CHOICE_URL = "http://localhost:8085/api/v1/addNewChoice";
const GET_CHOICE_URL = "http://localhost:8085/api/v1/getChoice";
class ChoiceService{
    saveChoice(choice){
        console.log(choice)
        return axios.post(ADD_CHOICE_URL, choice);
    }

    getChoice(){
        return axios.get(GET_CHOICE_URL);
    }
}

export default new ChoiceService();
