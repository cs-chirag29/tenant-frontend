-- Create table for tenants
CREATE TABLE tenants (
    tenant_id       BIGSERIAL PRIMARY KEY,
    first_name      VARCHAR(255) NOT NULL,
    last_name       VARCHAR(255) NOT NULL,
    email           VARCHAR(255) UNIQUE NOT NULL,
    phone_number    VARCHAR(15) NOT NULL,
    address         VARCHAR(255),
    city            VARCHAR(255),
    zipcode         VARCHAR(6),
    state           VARCHAR(255),
    role            VARCHAR(50) NOT NULL
);

-- Create table for properties
CREATE TABLE properties (
    property_id     BIGSERIAL PRIMARY KEY,  -- Use BIGSERIAL for auto-incrementing long values
    property_name   VARCHAR(255) NOT NULL,
    city            VARCHAR(100) NOT NULL,
    zip_code        VARCHAR(10) NOT NULL,
    address         VARCHAR(500) NOT NULL,
    state           VARCHAR(100) NOT NULL,
    description     VARCHAR(1000) NOT NULL
);

-- Create table for units
CREATE TABLE units (
    unit_id         BIGSERIAL PRIMARY KEY,  -- Use BIGSERIAL for auto-incrementing long values
    unit_number     VARCHAR(255) NOT NULL UNIQUE,  -- Unique constraint on unit_number
    unit_type       VARCHAR(255) NOT NULL,
    unit_image      VARCHAR(255) NOT NULL,
    rent_amount     NUMERIC(10, 2) NOT NULL,  -- Numeric type for monetary values
    status          VARCHAR(50) NOT NULL,
    property_id     BIGINT NOT NULL,

    -- Define foreign key constraint
    CONSTRAINT fk_property
        FOREIGN KEY (property_id) REFERENCES properties(property_id)
        ON DELETE CASCADE
);

-- Create table for user_logins
CREATE TABLE user_logins (
    id              BIGSERIAL PRIMARY KEY, -- Auto-incrementing long values for primary key
    username        VARCHAR(50) NOT NULL UNIQUE, -- Unique constraint on username
    password        VARCHAR(255) NOT NULL, -- Adjusted size for password storage
    tenant_id       BIGINT NOT NULL, -- Foreign key reference

    -- Define foreign key constraint
    CONSTRAINT fk_tenant
        FOREIGN KEY (tenant_id) REFERENCES tenants(tenant_id)
        ON DELETE CASCADE
);

-- Create table for orders
CREATE TABLE orders (
    order_id           SERIAL PRIMARY KEY,
    property_id        INTEGER NOT NULL,
    unit_id            INTEGER NOT NULL,
    tenant_id          INTEGER NOT NULL,
    lease_id           INTEGER,
    application_date   DATE NOT NULL,
    start_date         DATE NOT NULL,
    end_date           DATE NOT NULL,
    status             VARCHAR(50) NOT NULL CHECK (status IN ('Pending', 'Approved', 'Rejected')),
    rent_amount        NUMERIC(10, 2) NOT NULL,
    security_deposit   NUMERIC(10, 2) NOT NULL,
    created_at         TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at         TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    completed_at       TIMESTAMP,

    -- Define foreign key constraints
    CONSTRAINT fk_orders_property
        FOREIGN KEY (property_id) REFERENCES properties(property_id)
        ON DELETE CASCADE,

    CONSTRAINT fk_orders_unit
        FOREIGN KEY (unit_id) REFERENCES units(unit_id)
        ON DELETE CASCADE,

    CONSTRAINT fk_orders_tenant
        FOREIGN KEY (tenant_id) REFERENCES tenants(tenant_id)
        ON DELETE CASCADE,

    CONSTRAINT fk_orders_lease
        FOREIGN KEY (lease_id) REFERENCES leases(lease_id)
        ON DELETE SET NULL
);

-- Create table for leases
CREATE TABLE leases (
    lease_id          SERIAL PRIMARY KEY,
    start_date        DATE NOT NULL,
    end_date          DATE NOT NULL,
    security_deposit  NUMERIC(10, 2) NOT NULL,
    tenant_id         INTEGER NOT NULL,
    unit_id           INTEGER NOT NULL,
   -- order_id          INTEGER,

    -- Define foreign key constraints
    CONSTRAINT fk_leases_tenant
        FOREIGN KEY (tenant_id) REFERENCES tenants(tenant_id)
        ON DELETE CASCADE,

    CONSTRAINT fk_leases_unit
        FOREIGN KEY (unit_id) REFERENCES units(unit_id)
        ON DELETE CASCADE


);
