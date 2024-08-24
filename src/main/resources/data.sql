-- Insert data into the `supplier` table
INSERT INTO supplier (company_name, website, location, nature_of_business)
VALUES ('ABC Manufacturing', 'http://abcmanufacturing.com', 'India', 'SMALL_SCALE');

INSERT INTO supplier (company_name, website, location, nature_of_business)
VALUES ('XYZ Industries', 'http://xyzindustries.com', 'USA', 'LARGE_SCALE');

INSERT INTO supplier (company_name, website, location, nature_of_business)
VALUES ('PQR Corp', 'http://pqrcorp.com', 'Germany', 'MEDIUM_SCALE');

-- Insert data into the `supplier_processes` table using IDs from the supplier table
INSERT INTO supplier_processes (supplier_id, manufacturing_processes)
SELECT supplier_id, 'THREE_D_PRINTING' FROM supplier WHERE company_name = 'ABC Manufacturing';

INSERT INTO supplier_processes (supplier_id, manufacturing_processes)
SELECT supplier_id, 'CASTING' FROM supplier WHERE company_name = 'XYZ Industries';

INSERT INTO supplier_processes (supplier_id, manufacturing_processes)
SELECT supplier_id, 'MOULDING' FROM supplier WHERE company_name = 'PQR Corp';

-- Insert more data into the `supplier` table
INSERT INTO supplier (company_name, website, location, nature_of_business)
VALUES ('LMN Industries', 'http://lmnindustries.com', 'UK', 'SMALL_SCALE');

INSERT INTO supplier (company_name, website, location, nature_of_business)
VALUES ('OPQ Manufacturing', 'http://opqmanufacturing.com', 'Canada', 'LARGE_SCALE');

INSERT INTO supplier (company_name, website, location, nature_of_business)
VALUES ('RST Corp', 'http://rstcorp.com', 'Australia', 'MEDIUM_SCALE');

-- Insert data into the `supplier_processes` table using IDs from the supplier table

INSERT INTO supplier_processes (supplier_id, manufacturing_processes)
SELECT supplier_id, 'COATING' FROM supplier WHERE company_name = 'LMN Industries';

INSERT INTO supplier_processes (supplier_id, manufacturing_processes)
SELECT supplier_id, 'MOULDING' FROM supplier WHERE company_name = 'OPQ Manufacturing';

INSERT INTO supplier_processes (supplier_id, manufacturing_processes)
SELECT supplier_id, 'THREE_D_PRINTING' FROM supplier WHERE company_name = 'RST Corp';

-- Add more manufacturing processes to existing suppliers
INSERT INTO supplier_processes (supplier_id, manufacturing_processes)
SELECT supplier_id, 'CASTING' FROM supplier WHERE company_name = 'ABC Manufacturing';

INSERT INTO supplier_processes (supplier_id, manufacturing_processes)
SELECT supplier_id, 'COATING' FROM supplier WHERE company_name = 'XYZ Industries';
