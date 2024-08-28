import React from 'react';
import styles from './css/App.css';
import MatrixToolComponent from './components/MatrixToolComponent';

class App extends React.Component {

  constructor(props) {
    super(props)
    this.state = { started: false };
    this.start = this.start.bind(this);
  }

  start() {
    this.setState({ started: true });
  }

  render() {

    let started = this.state.started

    return (

      started ? <MatrixToolComponent /> :
        <div className="App">
          <div className="intro">
            <h1> The Cloud Native Maturity Matrix Assessment </h1>
            <p> This questionnaire will guide you through a Maturity Matrix self-assessment. It’s a
            lightweight, digitalized version of the same tool CS engineers may use when performing a
            Cloud Native Assessment, a multi-day dive into a company’s current systems,
            processes, and culture. Even though it’s much less detailed, this basic version will
            still provide an initial high-level snapshot of where you are right now—and a general
          flight path for your company’s own transformation. </p>

            <p>
              How do we assess current organisational status?
              First, you will answer a series of questions designed to reveal your company’s current
              status, from culture to process to infrastructure. We use the answers to draw a point
              on each of the nine separate axes.
            </p>

            <p>
              Once all nine areas have been assessed, we will present a graph and summary of each area to provide
              a powerful visual of your company’s current state.
            </p>
            <button class="start-button" onClick={this.start}><span> Start</span></button>
          </div>
        </div>
    )

  }
}

export default App;
