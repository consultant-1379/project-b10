import axios from 'axios'

const DATA_REST_API_URL = 'http://localhost:8080/question/all';
const RESULT_REST_POST_API_URL = 'http://localhost:8080/result/';

class StageService {

    getStageData() {
        return axios.get(DATA_REST_API_URL)
    }

    sendStageData(payload) {
        return axios({
            method: 'post',
            url: RESULT_REST_POST_API_URL,
            data: payload
        });
    }
}

export default new StageService();