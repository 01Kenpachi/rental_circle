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

images/UML DIAGRAM.jpg
