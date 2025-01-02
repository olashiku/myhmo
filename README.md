# MYHMO - Healthcare Communication Platform

## Overview
MYHMO is a mobile healthcare platform that facilitates direct communication between doctors and patients. The app streamlines medical consultations by providing a secure environment for remote health assessments and professional medical advice.

## Features
- **Secure Patient-Doctor Communication**
  - Real-time messaging
  - Secure file sharing for medical records
  - Appointment scheduling system

- **Medical Consultation**
  - Virtual consultations
  - Medical history tracking
  - Digital prescription management

- **User Management**
  - Separate interfaces for doctors and patients
  - Profile management
  - Appointment history

## Technical Stack
- **Frontend**
  - Language: Kotlin
  - Architecture: MVVM
  - UI: Material Design components
  - State Management: Coroutines/Flow

- **Key Libraries**
  - Koin for dependency injection
  - Retrofit for API communication
  - Room for local data persistence
  - Firebase for real-time messaging

## Security Features
- End-to-end encryption for messages
- Secure storage for medical records
- HIPAA compliance measures
- Authentication and authorization

## Installation
1. Clone the repository
```bash
git clone https://github.com/olashiku/My-Hmo.git
```

2. Open the project in Android Studio

3. Configure your local.properties file with required API keys

4. Build and run the application

## Prerequisites
- Android Studio Arctic Fox or later
- Minimum SDK: API 21 (Android 5.0)
- Target SDK: API 33
- Kotlin version: 1.8.0 or later



## Configuration
1. Set up your backend API endpoint in `local.properties`:
```properties
API_BASE_URL=your_api_url
```

2. Configure Firebase:
   - Add your `google-services.json`
   - Enable required Firebase services

## Contributing
1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## Contact
For support or queries, please contact [olashiku@gmail.com]

## Acknowledgments
- List any third-party libraries or resources used
- Credits to contributors
- Special thanks to the healthcare professionals who provided domain expertise
