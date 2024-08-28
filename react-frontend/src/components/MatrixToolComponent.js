import React from 'react';

import StageComponent from '../components/StageComponent';
import StageService from '../services/StageService';
import GraphComponent from './GraphComponent';
import {createPayload} from '../tools/tools';


class MatrixToolComponent extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            data: [],
            showGraph: false
        };
        this.onQuestionSubmit = this.onQuestionSubmit.bind(this);
    }

    componentDidMount() {
        StageService.getStageData().then((response) => {
            this.setState({
                userId: response.data["userId"],
                data: response.data["listOfAreaView"]
            })
        });
    }

    onQuestionSubmit(e) {
        e.preventDefault();
        let form = document.getElementById('toolForm');
        let formData = new FormData(form);
        let payload = createPayload(formData, this.state.userId);
        
        StageService.sendStageData(payload)
            .then((response) => {
                this.setState({ resultData: response.data, showGraph: true });
            }).catch((err) => console.log(err));
    }

    render() {

        return (
            <div>
                <form id="toolForm" name="toolForm" onSubmit={this.onQuestionSubmit}>
                    {this.state.data.map(stage => (

                        <StageComponent
                            key={stage.areaId}
                            data={stage}
                        />
                    ))}
                    <div className="submission-box">
                        <label for="name">Name:</label>
                        <input type="text" name="name" /><br></br>
                        <label for="company">Company:</label>
                        <input type="text" name="company" /><br></br>
                        <label for="email">Email:</label>
                        <input type="text" name="email" /><br></br>
                        <button className="start-button"><span>Submit</span></button>
                    </div>
                </form>
                { this.state.showGraph ? <GraphComponent data={this.state.resultData} /> : ""}
            </div>

        )
    }
}

export default MatrixToolComponent;