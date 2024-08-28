import React from 'react';


class SummaryComponent extends React.Component{


    render(){
        return(
            <div className="summary">
                <h3>{this.props.data.areaName}</h3>
                <p>Journey to Cloud Native: {(this.props.data.xCoordinate / 4) * 100}%</p>
                <p>Classification:{this.props.data.wordDescription}</p>
            </div>
        )
    }
}

export default SummaryComponent;