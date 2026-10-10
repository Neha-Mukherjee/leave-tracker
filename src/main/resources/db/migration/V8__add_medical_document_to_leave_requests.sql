ALTER TABLE leave_requests
    ADD COLUMN medical_document_name VARCHAR(255),
    ADD COLUMN medical_document_path VARCHAR(500);