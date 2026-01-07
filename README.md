# Rental Circle - Commodity Rental Management System

Java-based rental management solution for modern sharing economy businesses. This system enables entrepreneurs to efficiently manage rental operations across six commodity categories with comprehensive tracking and automated pricing.

## 🏢 Business Concept

Rental Circle transforms traditional rental businesses by digitizing inventory management and customer transactions. The system targets:
- **Equipment Rental Companies** (tools, vehicles)
- **Event Rental Services** (furniture, electronics)
- **Apparel Rental Platforms** (clothing, accessories)
- **General Rental Shops** expanding their offerings

The model addresses the growing sharing economy market by providing small-to-medium rental businesses with enterprise-grade management tools at minimal cost.

## ✨ Core Features

### Category-Specific Management
Six specialized commodity types (Electronics, Furniture, Vehicles, Clothing, Tools, Others) with unique attributes and cost structures

### Complete Rental Workflow
- **Customer Verification**: 9-digit NID validation for secure transactions
- **Smart Borrowing**: Category-based browsing with availability filtering
- **Automated Returns**: Damage assessment and penalty calculations
- **Financial Tracking**: Real-time profit monitoring and transaction history

### Dynamic Pricing Engine
- Base pricing with 20% profit margin
- Category-specific cost additions (transportation, maintenance, etc.)
- Discount system (DIS100 code for 10% off)
- Damage fee calculations based on item value

### Data Persistence
File-based storage system with three core files:
- `available.txt` - Current inventory
- `borrowed.txt` - Active rentals
- `payments.txt` - Transaction history

## 🏗️ Technical Architecture

The system exemplifies SOLID object-oriented principles:

**Abstraction**: `Commodity` base class with shared functionality  
**Inheritance**: Specialized classes for each commodity type  
**Polymorphism**: Overloaded pricing methods and category-specific behaviors  
**Encapsulation**: Protected data with controlled access methods  
**Exception Handling**: Custom `InvalidNIDException` for robust input validation

## 📁 Project Structure

- **`rentalCircle/MainApp.java`** - Main application entry point with console interface
- **`allCommodities/Commodity.java`** - Abstract base class for all commodities
- **`allCommodities/CommodityType.java`** - Enum defining the six commodity categories
- **`allCommodities/Electronics.java`** - Electronics-specific implementation
- **`allCommodities/Furniture.java`** - Furniture-specific implementation
- **`allCommodities/Vehicle.java`** - Vehicle-specific implementation
- **`allCommodities/Clothing.java`** - Clothing-specific implementation
- **`allCommodities/Tools.java`** - Tools-specific implementation
- **`allCommodities/OtherCommodity.java`** - Miscellaneous items implementation
- **`allCommodities/Pricing.java`** - Pricing interface
- **`customexception/InvalidNIDException.java`** - Custom exception for NID validation
- **`fileHandle/FileService.java`** - File I/O operations handler
- **`system/RentalSystem.java`** - Core business logic and system operations


![UML Diagram](UML%20DIAGRAM.jpg)

## 🚀 Getting Started

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Basic terminal/command prompt knowledge

### Installation & Usage

1. Clone the repository
git clone https://github.com/01Kenpachi/rental-circle.git

2. Navigate to project directory
cd rental-circle

3. Compile the application
javac rentalCircle/MainApp.java

4. Run the system
java rentalCircle/MainApp

5. Login with demo credentials
   
     - **USERNAME:** admin

     - **PASSWORD:** admin123

### Quick Security Upgrade Steps
For transitioning from demo to production:
- Remove hardcoded credentials from MainApp.java
- Create a users.properties file (excluded from Git)
- Implement SHA-256 hashing for passwords
- Add user registration functionality

### 💼 Commercialization Pathway
#### Phase 1: Enterprise Features
- **Database Migration**: Replace file storage with PostgreSQL/MySQL
- **Multi-User Support**: Role-based access (Admin, Staff, Customer)
- **Web Interface**: Spring Boot backend with React frontend
- **Payment Integration**: Stripe/PayPal for online payments

#### Phase 2: Advanced Capabilities
- **Mobile App**: Android/iOS applications for customer bookings
- **Inventory Tracking**: Barcode/QR code scanning
- **Analytics Dashboard**: Business intelligence reports
- **Multi-Branch Support**: Centralized management for chain stores

#### Phase 3: Market Expansion
- **API Development**: RESTful API for third-party integrations
- **Subscription Model**: SaaS pricing tiers
- **White-label Solution**: Custom branding for rental businesses
- **Internationalization**: Multi-currency and language support

### 🔄 Customization Guide
#### For Different Business Models:
- **Equipment Rental**: Modify `Tools.java` and `Vehicle.java` with industry-specific fields
- **Event Rentals**: Add `Events` category with date-based booking system
- **Apparel Rental**: Enhance `Clothing.java` with size charts and cleaning schedules
- **Subscription Boxes**: Implement recurring rental logic

#### Quick Modifications:
- **Pricing Strategy**: Adjust profit margins in `Commodity.java`
- **Category Costs**: Update `getCategoryCost()` in each commodity class
- **Discount Codes**: Extend `calculateTotalRent()` methods in `Commodity.java`
- **Customer Validation**: Modify `validateNID()` in `RentalSystem.java`

### ⚠️ Current Limitations & Solutions

| Current Limitation | Commercial Solution |
| :--- | :--- |
| **File-based storage** | Migrate to SQL database |
| **Hardcoded credentials** | User management system |
| **Single-user access** | Multi-user authentication |
| **Console interface** | Web/mobile applications |
| **No backup system** | Cloud backup integration |
| **Limited search** | Elasticsearch implementation |

## 📈 Business Potential

### Unlock the Future of the Sharing Economy
Rental Circle provides entrepreneurs with a business model for the rapidly growing rental market. As consumer behavior shifts from ownership to access, this system offers a turnkey solution to launch or scale a profitable rental business with minimal investment.

#### Why This Market is Exploding:
- **Urbanization & Space Constraints**: 68% of urban dwellers prefer renting over buying due to limited storage space
- **Sustainability Movement**: Conscious consumers embrace sharing economy to reduce environmental footprint
- **Post-Pandemic Mindset**: Preference for temporary access over permanent ownership is at an all-time high
- **Digital Natives**: Millennials and Gen Z drive demand for app-based rental services
- **Flexibility Economy**: Businesses and individuals seek adaptable asset management solutions


