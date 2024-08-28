import React from 'react';

class QuestionComponent extends React.Component {

    constructor(props) {
        super(props);
        this.state = { value: 0 }
    }

    onChange(e) {
        this.setState({ value: Number(e.target.value) });
    }

    render() {
        return (
            <div className="question">
                <p>{this.props.data.name}</p>
                <label className="button-container">Yes
                <input type="radio" value="1" onChange={this.onChange.bind(this)} name={this.props.data.area.areaId + "-" + this.props.data.questionId} />
                    <span className="checkmark"></span>
                </label>
                <label className="button-container">No
                <input type="radio" value="0" onChange={this.onChange.bind(this)} name={this.props.data.area.areaId + "-" + this.props.data.questionId} defaultChecked />
                    <span className="checkmark"></span>
                </label>
            </div>
        )
    }

}

export default QuestionComponent;