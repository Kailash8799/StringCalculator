# String Calculator

## Overview

String Calculator is a simple Java application that implements a calculator that can handle string inputs for arithmetic operations. It was designed following the principles of Test-Driven Development (TDD) and supports various input formats, including custom delimiters and newline-separated values.

## Features

- **Handles Empty Input**: Returns `0` for an empty string.
- **Addition of Up to Two Numbers**: Supports addition for zero, one, or two numbers.
- **Supports Unknown Amount of Numbers**: Can handle an unknown number of integers.
- **New Line Support**: Supports numbers separated by commas and new lines (e.g., `1\n2,3`).
- **Custom Delimiters**: Allows custom delimiters specified in the input (e.g., `//[delimiter]\n[numbers...]`).
- **Negative Number Handling**: Throws an exception for negative numbers with details.
- **Ignores Large Numbers**: Ignores numbers greater than `1000`.
- **Event Notifications**: Triggers events after each addition call.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- Maven (for dependency management)

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/Kailash8799/StringCalculator.git
   cd StringCalculator
