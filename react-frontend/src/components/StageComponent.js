import React from 'react';
import InformationComponent from './InformationComponent';
import QuestionComponent from './QuestionComponent'

class StageComponent extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            showSubQuestions: false,
        };
    }


    onChange(e) {
        let val = Number(e.target.value);
        this.setState({ showSubQuestions: !val });
    }

    render() {

        let show = this.state.showSubQuestions;

        return (
            <div className="stage" key={this.props.areaId}>
                <h1>{this.props.data.nameOfArea}</h1>
                <h2>{this.props.data.description}</h2>
                <p>{this.props.data.topLevelQuestion}</p>
                <label className="area-button-container">Yes
                    <input onChange={this.onChange.bind(this)} type="radio" value="1" name={this.props.data.areaId} defaultChecked />
                    <span className="checkmark"></span>
                </label>
                <label className="area-button-container">No
                    <input onChange={this.onChange.bind(this)} type="radio" value="0" name={this.props.data.areaId} />
                    <span className="checkmark"></span>
                </label>
                {show ? <InformationComponent /> : ""}
                 
                {show ?
                
                    this.props.data.subQuestions.map(question => (
                        <QuestionComponent
                            key={question.questionId}
                            data={question}
                        />
                    )) :
                    ""}

            </div>
        )
    }
}


export default StageComponent;