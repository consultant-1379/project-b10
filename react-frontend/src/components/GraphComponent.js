import React from 'react';
import { AreaChart, YAxis, XAxis, CartesianGrid, Label, Area, Legend } from 'recharts';
import SummaryComponent from './SummaryComponent';

class GraphComponent extends React.Component {

    componentDidMount() {
        document.getElementById("results-graph").scrollIntoView();
    }

    render() {
        const maxValue = 4;
        let mapIndex = 0;
        this.props.data.forEach(element => {
            element["Cloud Native"] = maxValue;
            let val = element["xCoordinate"];
            element["Assessment Result"] = val;
        });
        const data = this.props.data;

        return (
            <div id="results-graph">
                <AreaChart layout="vertical" data={data} width={700} height={700}
                    margin={{ top: 100, right: 100, left: 100, bottom: 100 }}>
                    <XAxis type="number" domain={[1, 5]} orientation="top">
                        <Label value="The Maturity Matrix" offset={20} position="top" />
                    </XAxis>
                    <YAxis type="category" dataKey="areaName" />
                    <CartesianGrid />
                    <Area type="monotone" dataKey="Cloud Native" stroke="#ffc8dd" fill="#ffc8dd" />
                    <Area type="monotone" dot={true} dataKey="Assessment Result" stroke="#9999ff" fill="#9999ff" />
                    <Legend />
                </AreaChart>
                {data.map(area => (
                    <SummaryComponent key={mapIndex++} data={area} />
                ))}
            </div>
        )
    }

}
export default GraphComponent;