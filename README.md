# Inventory Management System

A comprehensive Java-based inventory management system designed for equipment rental operations, featuring a normalized database schema with full CRUD functionality and business intelligence reporting.

## Table of Contents
- [Overview](#overview)
- [Features](#features)
- [Technology Stack](#technology-stack)
- [Database Schema](#database-schema)
- [Installation](#installation)
- [Usage](#usage)
- [Key Functionalities](#key-functionalities)
- [Database Queries](#database-queries)
- [Contributors](#contributors)

## Overview

This project was developed as part of **CSE 3242: Introduction to Database Systems** at The Ohio State University. The system manages equipment rental operations including customer management, inventory tracking, drone deliveries, and comprehensive reporting capabilities.

## Features

- **Full CRUD Operations**: Complete Create, Read, Update, Delete functionality for all entities
- **Normalized Database Design**: 13 interconnected tables in Boyce-Codd Normal Form (BCNF)
- **Advanced SQL Queries**: Complex joins, subqueries, aggregations, and analytical reports
- **Performance Optimization**: Strategic indexing on high-frequency query fields
- **Transaction Management**: Ensures data consistency across multi-step operations
- **Business Intelligence**: Automated reporting for rental patterns and equipment utilization
- **GUI Interface**: User-friendly Java application for system interaction

## Technology Stack

- **Programming Language**: Java
- **Database**: SQLite with embedded SQL
- **Development Environment**: Eclipse IDE
- **Database Design**: EasyEDA for schema modeling
- **Version Control**: Git

## Database Schema

The system implements a normalized database with 13 core tables:

### Core Entities
- **Customer**: Customer information and account management
- **RentalOrder**: Equipment rental transactions
- **Inventory**: Equipment and drone inventory tracking
- **Equipment**: Rentable equipment specifications
- **Drone**: Delivery drone specifications
- **Warehouse**: Storage facility management

### Relationship Tables
- **Allocation**: Equipment-to-rental assignments
- **Delivery**: Drone delivery tracking
- **Payment**: Rental payment processing
- **Review**: Customer equipment reviews

### Supporting Tables
- **Supplier**: Equipment procurement tracking
- **PurchaseOrder**: Inventory acquisition management
- **Distance**: Customer-warehouse proximity calculations

## Installation

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- SQLite JDBC driver
- Eclipse IDE (recommended)

### Setup Steps
1. Clone the repository:
   ```bash
   git clone [repository-url]
   cd inventory-management-system
2. Import the project into Eclipse:
   - File → Import → Existing Projects into Workspace
   - Select the project directory

3. Add SQLite JDBC to classpath:
   - Right-click project → Properties → Java Build Path → Libraries
   - Add External JARs → Select sqlite-jdbc jar file

4. Run the database setup:
   - Execute `create.sql` to create database schema
   - Execute `populate.sql` to insert sample data

## Usage

### Starting the Application
1. Run the main Java application file
2. The GUI interface will launch
3. Use the navigation menu to access different functionalities

### Core Operations
- **Equipment Management**: Add, modify, remove, and track equipment
- **Customer Management**: Register customers and manage accounts
- **Rental Processing**: Create and process equipment rentals
- **Return Handling**: Process equipment returns and calculate fees
- **Delivery Coordination**: Assign drones for equipment delivery
- **Reporting**: Generate business analytics and performance reports

## Key Functionalities

### Equipment Management
- Add new equipment to inventory
- Track equipment status and location
- Monitor equipment utilization rates
- Manage equipment maintenance schedules

### Rental Operations
- Process customer rental requests
- Calculate rental fees and due dates
- Track equipment allocation and returns
- Handle overdue rental notifications

### Business Intelligence
- **Daily Rentals View**: Track daily rental volume and revenue
- **Overdue Rentals View**: Monitor past-due equipment
- **Popular Equipment Reports**: Analyze rental frequency by item
- **Customer Analytics**: Track customer rental patterns

## Database Queries

The system includes sophisticated SQL queries for various operations:

### Sample Advanced Queries
- Equipment utilization analysis by manufacturer
- Customer rental history with equipment details
- Drone delivery performance metrics
- Revenue analysis by time period and equipment type
- Inventory aging reports
- Customer lifetime value calculations

### Performance Features
- **Optimized Indexing**: Strategic indexes on CustomerID, RentalID, and EquipmentSerial
- **Database Views**: Pre-computed views for frequent analytical queries
- **Transaction Safety**: ACID compliance for all multi-table operations
```
## Project Structure
├── src/
│   ├── main/
│   │   ├── java/           # Java application code
│   │   └── resources/      # Configuration files
├── database/
│   ├── create.sql          # Database schema creation
│   ├── populate.sql        # Sample data insertion
│   ├── queries.sql         # Standard query examples
│   ├── advanced-queries.sql # Complex analytical queries
│   └── extra-queries.sql   # Additional query examples
├── docs/
│   └── final-report.pdf    # Comprehensive project documentation
└── README.md
```
## Contributors

**Team Members** (Ohio State University - CSE 3242):
- Blake Theis
- Christopher McDevitt
- Matthew DiSanto
- Sam Schmitt

**Project Completion**: December 2024
